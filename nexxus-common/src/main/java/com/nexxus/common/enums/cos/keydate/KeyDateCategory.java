package com.nexxus.common.enums.cos.keydate;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeyDateCategory implements BaseEnum {
    ZONING("zoning", "Zoning"),
    POWER("power", "Power"),
    TRANSACTION("transaction", "Transaction"),
    ENGINEERING("engineering", "Engineering"),
    INVESTMENT("investment", "Investment"),
    ;

    private final String value;
    private final String desc;
}
