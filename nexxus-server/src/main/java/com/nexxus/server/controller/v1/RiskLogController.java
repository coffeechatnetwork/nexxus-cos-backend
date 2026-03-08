package com.nexxus.server.controller.v1;

import com.nexxus.cos.api.RiskLogApi;
import com.nexxus.cos.api.dto.risklog.CreateRiskLogRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogDto;
import com.nexxus.cos.api.dto.risklog.RiskLogListRequest;
import com.nexxus.cos.api.dto.risklog.RiskLogListResponse;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryDto;
import com.nexxus.cos.api.dto.risklog.RiskLogSummaryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/risk-logs")
@RequiredArgsConstructor
public class RiskLogController {

    private final RiskLogApi riskLogApi;

    @PostMapping("")
    public RiskLogDto createRiskLog(@RequestBody @Valid CreateRiskLogRequest req) {
        return riskLogApi.createRiskLog(req);
    }

    @PostMapping("/list")
    public RiskLogListResponse listRiskLogs(@RequestBody @Valid RiskLogListRequest req) {
        return riskLogApi.listRiskLogs(req);
    }

    @PostMapping("/summary")
    public RiskLogSummaryDto summary(@RequestBody @Valid RiskLogSummaryRequest req) {
        return riskLogApi.summary(req);
    }
}
