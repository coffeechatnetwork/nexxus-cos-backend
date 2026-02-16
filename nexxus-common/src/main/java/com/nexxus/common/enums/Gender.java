package com.nexxus.common.enums;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender implements BaseEnum {
    MALE("M", "Male"),
    FEMALE("F", "Female")
    ;

    private final String value;
    private final String desc;

}
