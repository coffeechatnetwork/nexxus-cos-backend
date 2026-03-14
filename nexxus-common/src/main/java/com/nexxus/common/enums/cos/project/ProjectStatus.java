package com.nexxus.common.enums.cos.project;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProjectStatus implements BaseEnum {
    ACTIVE("active", "Active"),
    ;

    private final String value;
    private final String desc;
}
