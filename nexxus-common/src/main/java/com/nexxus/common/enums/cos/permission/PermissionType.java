package com.nexxus.common.enums.cos.permission;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType implements BaseEnum {
    API("api", "API"),
    BUTTON("button", "Button"),
    MENU("menu", "Menu"),
    ;

    private final String value;
    private final String desc;
}
