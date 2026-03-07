package com.nexxus.common.enums.cos.risklog;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RiskLogLevel implements BaseEnum {
    LOW("low", "Low"),
    MEDIUM("medium", "Medium"),
    HIGH("high", "High"),
    NA("na", "N/A"),
    TBD("tbd", "TBD"),
    ;

    private final String value;
    private final String desc;
}

