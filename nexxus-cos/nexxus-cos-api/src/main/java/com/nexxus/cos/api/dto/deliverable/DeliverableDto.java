package com.nexxus.cos.api.dto.deliverable;

import com.nexxus.common.enums.cos.deliverable.DeliverableStatus;
import com.nexxus.common.vo.Attachment;
import com.nexxus.cos.api.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DeliverableDto implements Serializable {
    private String displayId;
    private String title;
    private String shortDesc;
    private String longDesc;
    private UserDto assignee;
    private Instant deadline;
    private List<UserDto> participants;
    private DeliverableStatus status;
    private List<Attachment> attachments;
    private UserDto createdBy;
    private UserDto updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
}
