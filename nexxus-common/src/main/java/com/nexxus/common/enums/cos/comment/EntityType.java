package com.nexxus.common.enums.cos.comment;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType implements BaseEnum {
    DELIVERABLE("deliverable", "Deliverable"),
    TASK("task", "Task"),
    DOCUMENT("document", "Document"),
    ;

    private final String value;
    private final String desc;
}
