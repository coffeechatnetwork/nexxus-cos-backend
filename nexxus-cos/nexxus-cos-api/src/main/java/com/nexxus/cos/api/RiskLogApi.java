package com.nexxus.cos.api;

import com.nexxus.cos.api.dto.risklog.CreateRiskLogRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogDto;
import com.nexxus.cos.api.dto.risklog.RiskLogListRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogListResponse;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryDto;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryRequest;

public interface RiskLogApi {
    RiskLogDto createRiskLog(Long projectId, CreateRiskLogRequest req);

    RiskLogListResponse listRiskLogs(Long projectId, RiskLogListRequest req);

    RiskLogSummaryDto summary(Long projectId, RiskLogSummaryRequest req);
}
