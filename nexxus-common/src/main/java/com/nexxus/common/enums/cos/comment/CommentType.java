package com.nexxus.common.enums.cos.comment;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentType implements BaseEnum {
    COMMENT("comment", "Comment"),
    ALERT("alert", "Alert"),
    SYSTEM_NOTIFICATION("system_notification", "System Notification"),
    INTERNAL_NOTE("internal_note", "Internal Note"),
    ;

    private final String value;
    private final String desc;
}
