package com.nexxus.common.enums.auth;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppCode implements BaseEnum {
    BOS("bos", "BuildingOS"),
    COS("cos", "ConsultingOS"),
    ;

    private final String value;
    private final String desc;
}
