package com.nexxus.common.enums.cos.comment;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType implements BaseEnum {
    DELIVERABLE("deliverable", "Deliverables"),
    KEY_DATE("key_date", "Key Dates"),
    TASK("task", "Tasks"),
    QA("qa", "Q&A"),
    DOCUMENT("document", "Documents"),
    ;

    private final String value;
    private final String desc;
}
