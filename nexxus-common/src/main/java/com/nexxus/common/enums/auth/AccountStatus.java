package com.nexxus.common.enums.auth;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus implements BaseEnum {
    ACTIVE("active", "Active"),
    INACTIVE("inactive", "Inactive"),
    ;

    private final String value;
    private final String desc;
}
