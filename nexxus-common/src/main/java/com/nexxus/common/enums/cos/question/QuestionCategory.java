package com.nexxus.common.enums.cos.question;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionCategory implements BaseEnum {
    AHJ("ahj", "AHJ"),
    ZONING("zoning", "Zoning"),
    ENVIRONMENTAL("environmental", "Environmental"),
    UTILITY_POWER("utility_power", "Utility Power"),
    NATURAL_GAS_POWER("natural_gas_power", "Natural Gas Power"),
    FIBER("fiber", "Fiber"),
    WATER("water", "Water"),
    WASTEWATER("wastewater", "Wastewater"),
    OTHER("other", "Other"),
    ;

    private final String value;
    private final String desc;
}
