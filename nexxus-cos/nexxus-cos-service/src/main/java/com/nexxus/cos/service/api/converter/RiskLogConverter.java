package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.risklog.RiskLogDto;
import com.nexxus.cos.api.dto.risklog.RiskLogListItem;
import com.nexxus.cos.service.entity.RiskLogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RiskLogConverter {

    public RiskLogDto toRiskLogDto(RiskLogEntity entity) {
        return RiskLogDto.builder()
                .projectId(entity.getProjectId())
                .displayId(entity.getDisplayId())
                .topic(entity.getTopic())
                .description(entity.getDescription())
                .risk(entity.getRisk())
                .mitigationOfRisk(entity.getMitigationOfRisk())
                .category(entity.getCategory())
                .level(entity.getLevel())
                .build();
    }

    public RiskLogListItem toRiskLogListItem(RiskLogEntity entity) {
        return RiskLogListItem.builder()
                .projectId(entity.getProjectId())
                .displayId(entity.getDisplayId())
                .topic(entity.getTopic())
                .description(entity.getDescription())
                .risk(entity.getRisk())
                .mitigationOfRisk(entity.getMitigationOfRisk())
                .category(entity.getCategory())
                .level(entity.getLevel())
                .build();
    }

}
