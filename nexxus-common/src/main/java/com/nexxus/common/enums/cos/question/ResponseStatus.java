package com.nexxus.common.enums.cos.question;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus implements BaseEnum {
    DRAFT("draft", "Draft"),
    PUBLISHED("published", "Published"),
    ;
    private final String value;
    private final String desc;
}
