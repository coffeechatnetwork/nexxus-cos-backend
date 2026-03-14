package com.nexxus.common.enums.cos;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SectionName implements BaseEnum {
    OVERDUE("overdue", "Overdue"),
    NEXT_7_DAYS("next_7_days", "Next 7 Days"),
    NEXT_30_DAYS("next_30_days", "Next 30 Days"),
    LATER("later", "Later"),
    ;

    private final String value;
    private final String desc;
}
