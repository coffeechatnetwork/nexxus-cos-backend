package com.nexxus.cos.service.api;

import com.nexxus.cos.api.RiskLogApi;
import com.nexxus.cos.api.dto.risklog.CreateRiskLogRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogDto;
import com.nexxus.cos.api.dto.risklog.RiskLogListRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogListResponse;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryDto;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryRequest;
import com.nexxus.cos.service.service.RiskLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RiskLogApiImpl implements RiskLogApi {

    private final RiskLogService riskLogService;

    @Override
    public RiskLogDto createRiskLog(CreateRiskLogRequest req) {
        return null;
    }

    @Override
    public RiskLogListResponse listRiskLogs(RiskLogListRequest req) {
        return null;
    }

    @Override
    public RiskLogSummaryDto summary(RiskLogSummaryRequest req) {
        return null;
    }
}
