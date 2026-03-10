package com.nexxus.cos.api.dto.risklog;

import com.nexxus.common.enums.cos.risklog.RiskLogLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RiskLogSummaryDto implements Serializable {

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RiskLogSummaryItem implements Serializable {
        private RiskLogLevel level;
        private Long count;
    }

    private List<RiskLogSummaryItem> items;
}
