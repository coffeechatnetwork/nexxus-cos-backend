package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.common.enums.cos.risklog.RiskLogCategory;
import com.nexxus.cos.api.dto.risklog.RiskLogCategoryList;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryDto;
import com.nexxus.cos.service.entity.RiskLogEntity;

import java.util.List;

public interface RiskLogService extends IService<RiskLogEntity> {
    RiskLogEntity getByProjectIdAndTopic(Long projectId, String topic);

    List<RiskLogEntity> getRiskLogsByProjectId(Long projectId);

    List<RiskLogCategoryList> listRiskLogs(Long projectId, RiskLogCategory category);

    List<RiskLogSummaryDto.RiskLogSummaryItem> summary(Long projectId, RiskLogCategory category);
}
