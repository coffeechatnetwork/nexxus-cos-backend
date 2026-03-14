package com.nexxus.common.enums.cos.deliverable;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliverableStatus implements BaseEnum {
    NOT_YET_STARTED("not_yet_started", "Not Yet Started"),
    IN_PROGRESS("in_progress", "In Progress"),
    NEARLY_COMPLETE("nearly_complete", "Nearly Complete"),
    COMPLETE("complete", "Complete")
    ;
    private final String value;
    private final String desc;
}
