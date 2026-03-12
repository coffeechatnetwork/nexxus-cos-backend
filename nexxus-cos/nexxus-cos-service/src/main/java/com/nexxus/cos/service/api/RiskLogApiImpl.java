package com.nexxus.cos.service.api;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.cos.api.RiskLogApi;
import com.nexxus.cos.api.dto.risklog.CreateRiskLogRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogCategoryList;
import com.nexxus.cos.api.dto.risklog.RiskLogDto;
import com.nexxus.cos.api.dto.risklog.RiskLogListRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogListResponse;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryDto;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryRequest;
import com.nexxus.cos.service.api.converter.RiskLogConverter;
import com.nexxus.cos.service.entity.RiskLogEntity;
import com.nexxus.cos.service.service.RiskLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RiskLogApiImpl implements RiskLogApi {

    private final RiskLogService riskLogService;
    private final RiskLogConverter riskLogConverter;

    @Override
    public RiskLogDto createRiskLog(Long projectId, CreateRiskLogRequest req) {
        AccountInfo accountInfo = AccountInfoContext.get();
        RiskLogEntity riskLogEntity = riskLogService.getByProjectIdAndTopic(projectId, req.getTopic());
        if (riskLogEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("riskLog already exist"));
        }
        RiskLogEntity newRiskLog = RiskLogEntity.builder()
                .orgId(accountInfo.getOrgId())
                .projectId(projectId)
                .displayId(UUID.randomUUID().toString())
                .topic(req.getTopic())
                .description(req.getDescription())
                .risk(req.getRisk())
                .category(req.getCategory())
                .mitigationOfRisk(req.getMitigationOfRisk())
                .category(req.getCategory())
                .level(req.getLevel())
                .build();
        riskLogService.save(newRiskLog);
        return riskLogConverter.toRiskLogDto(newRiskLog);
    }

    @Override
    public RiskLogListResponse listRiskLogs(Long projectId, RiskLogListRequest req) {
        List<RiskLogCategoryList> categories = riskLogService.listRiskLogs(projectId, req.getCategory());

        return RiskLogListResponse.builder()
                .categories(categories)
                .build();
    }

    @Override
    public RiskLogSummaryDto summary(Long projectId, RiskLogSummaryRequest req) {
        var categories = riskLogService.summary(projectId, req.getCategory());

        return RiskLogSummaryDto.builder()
                .items(categories)
                .build();
    }
}
