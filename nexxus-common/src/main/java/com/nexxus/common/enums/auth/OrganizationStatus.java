package com.nexxus.common.enums.auth;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrganizationStatus implements BaseEnum {
    ACTIVE("active", "Active"),
    TERMINATED("terminated", "Terminated"),
    ;

    private final String value;
    private final String desc;
}
