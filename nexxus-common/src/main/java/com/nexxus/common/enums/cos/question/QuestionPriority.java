package com.nexxus.common.enums.cos.question;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionPriority implements BaseEnum {
    CRITICAL("critical", "Critical"),
    HIGH("high", "High"),
    MEDIUM("medium", "Medium"),
    LOW("low", "Low"),
    ;

    private final String value;
    private final String desc;
}
