package com.nexxus.common.enums.auth;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType implements BaseEnum {
    BOS_MEMBER("bos_member", "Building OS Member"),
    COS_MEMBER("cos_member", "Consulting OS Member"),
    ;

    private final String value;
    private final String desc;
}
