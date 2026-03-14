package com.nexxus.common.enums.cos.permission;

import com.nexxus.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationCode implements BaseEnum {
    CREATE("create", "Create"),
    VIEW("view", "View"),
    EDIT("edit", "Edit"),
    DELETE("delete", "Delete"),

    ASSIGN("assign", "Assign"),
    RESOLVE("resolve", "Resolve"),
    APPROVE("approve", "Approve"),
    REJECT("reject", "Reject"),

    EXPORT("export", "Export"),
    IMPORT("import", "Import"),

    ARCHIVE("archive", "Archive"),
    PUBLISH("publish", "Publish"),
    CLOSE("close", "Close"),
    REOPEN("reopen", "Reopen"),

    MANAGE("manage", "Manage"),
    ;

    private final String value;
    private final String desc;
}
