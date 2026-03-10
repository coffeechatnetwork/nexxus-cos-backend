package com.nexxus.cos.api.dto.risklog;

import com.nexxus.common.enums.cos.risklog.RiskLogCategory;
import com.nexxus.common.enums.cos.risklog.RiskLogLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRiskLogRequest implements Serializable {
    @NotNull
    private Long projectId;
    @NotBlank
    private String topic;
    private String description;
    private String risk;
    private String mitigationOfRisk;
    @NotNull
    private RiskLogCategory category;
    @NotNull
    private RiskLogLevel level;
}
