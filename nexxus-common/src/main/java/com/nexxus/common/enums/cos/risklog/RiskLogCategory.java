package com.nexxus.common.enums.cos.risklog;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RiskLogCategory implements BaseEnum {
    REGIONAL_AND_ECONOMIC("regional_and_economic", "Regional & Economic"),
    JURISDICTIONAL("jurisdictional", "Jurisdictional"),
    SITE_CONDITIONS_AND_CONSTRAINTS("site_conditions_and_constraints", "Site Conditions and Constraints"),
    ENVIRONMENTAL("environmental", "Environmental"),
    UTILITIES("utilities", "Utilities"),
    OTHER("other", "Other"),
    ;

    private final String value;
    private final String desc;
}
