package com.nexxus.cos.api.dto.risklog;

import com.nexxus.common.enums.cos.risklog.RiskLogCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RiskLogSummaryRequest implements Serializable {
    private Long projectId;
    private RiskLogCategory category;
}
