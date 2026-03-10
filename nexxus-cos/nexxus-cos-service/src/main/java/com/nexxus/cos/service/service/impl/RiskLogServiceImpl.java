package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.common.enums.cos.risklog.RiskLogCategory;
import com.nexxus.common.enums.cos.risklog.RiskLogLevel;
import com.nexxus.cos.api.dto.risklog.RiskLogCategoryList;
import com.nexxus.cos.api.dto.risklog.RiskLogListItem;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryDto;
import com.nexxus.cos.service.api.converter.RiskLogConverter;
import com.nexxus.cos.service.entity.RiskLogEntity;
import com.nexxus.cos.service.mapper.RiskLogMapper;
import com.nexxus.cos.service.service.RiskLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskLogServiceImpl extends ServiceImpl<RiskLogMapper, RiskLogEntity> implements RiskLogService {

    private final RiskLogConverter riskLogConverter;

    @Override
    public RiskLogEntity getByProjectIdAndTopic(Long projectId, String topic) {
        return lambdaQuery()
                .eq(RiskLogEntity::getProjectId, projectId)
                .eq(RiskLogEntity::getTopic, topic)
                .one();
    }

    @Override
    public List<RiskLogEntity> getRiskLogsByProjectId(Long projectId) {
        return lambdaQuery()
                .eq(RiskLogEntity::getProjectId, projectId)
                .list();
    }

    @Override
    public List<RiskLogCategoryList> listRiskLogs(Long projectId, RiskLogCategory category) {
        List<RiskLogEntity> riskLogs = getRiskLogsByProjectId(projectId);

        if (riskLogs == null || riskLogs.isEmpty()) {
            return List.of();
        }
        if (category != null) {
            riskLogs = riskLogs.stream()
                    .filter(riskLog -> riskLog.getCategory() == category)
                    .toList();
        }

        Map<RiskLogCategory, List<RiskLogEntity>> groupedRiskLogs = riskLogs.stream()
                .collect(Collectors.groupingBy(RiskLogEntity::getCategory));

        return groupedRiskLogs.entrySet().stream()
                .map(entry -> {
                    List<RiskLogListItem> items = entry.getValue().stream()
                            .map(riskLogConverter::toRiskLogListItem)
                            .collect(Collectors.toList());
                    return RiskLogCategoryList.builder()
                            .category(entry.getKey())
                            .items(items)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<RiskLogSummaryDto.RiskLogSummaryItem> summary(Long projectId, RiskLogCategory category) {
        List<RiskLogEntity> riskLogs = getRiskLogsByProjectId(projectId);

        if (category != null) {
            riskLogs = riskLogs.stream()
                    .filter(riskLog -> riskLog.getCategory() == category)
                    .toList();
        }

        Map<RiskLogLevel, List<RiskLogEntity>> groupedByLevel = riskLogs.stream()
                .collect(Collectors.groupingBy(RiskLogEntity::getLevel));

        return Stream.of(RiskLogLevel.values())
                .map(level -> {
                    List<RiskLogEntity> levelRiskLogs = groupedByLevel.getOrDefault(level, List.of());
                    List<RiskLogListItem> listItem = levelRiskLogs.stream()
                            .map(riskLogConverter::toRiskLogListItem)
                            .toList();
                    return RiskLogSummaryDto.RiskLogSummaryItem.builder()
                            .level(level)
                            .count((long) listItem.size())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
