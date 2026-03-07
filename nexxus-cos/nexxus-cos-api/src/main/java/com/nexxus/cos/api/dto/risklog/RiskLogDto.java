package com.nexxus.cos.api.dto.risklog;

import com.nexxus.common.enums.cos.risklog.RiskLogCategory;
import com.nexxus.common.enums.cos.risklog.RiskLogLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RiskLogDto implements Serializable {
    private Long projectId;
    private String displayId;
    private String topic;
    private String description;
    private String risk;
    private String mitigationOfRisk;
    private RiskLogCategory category;
    private RiskLogLevel level;
}
