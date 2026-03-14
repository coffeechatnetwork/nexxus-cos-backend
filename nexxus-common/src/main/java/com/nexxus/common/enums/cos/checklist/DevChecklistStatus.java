package com.nexxus.common.enums.cos.checklist;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DevChecklistStatus implements BaseEnum {
    OUTSTANDING("outstanding", "Outstanding"),
    IN_PROGRESS("in_progress", "In Progress"),
    NEARLY_COMPLETE("nearly_complete", "Nearly Complete"),
    COMPLETE("complete", "Complete"),
    ;

    private final String value;
    private final String desc;
}
