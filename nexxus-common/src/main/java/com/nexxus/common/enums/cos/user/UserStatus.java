package com.nexxus.common.enums.cos.user;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus implements BaseEnum {
    ACTIVE("active", "Active"),
    INACTIVE("inactive", "Inactive"),
    ;

    private final String value;
    private final String desc;
}
