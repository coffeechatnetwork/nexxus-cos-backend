package com.nexxus.cos.api.dto.checklist;

import com.nexxus.common.enums.cos.checklist.DevChecklistStatus;
import com.nexxus.cos.api.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DevChecklistListItem implements Serializable {
    private String displayId;
    private String name;
    private DevChecklistStatus status;
    private String waitingOn;
    private UserDto createdBy;
    private Instant createdAt;
    private UserDto updatedBy;
    private Instant updatedAt;
}
