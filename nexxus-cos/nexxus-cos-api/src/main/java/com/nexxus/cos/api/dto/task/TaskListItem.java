package com.nexxus.cos.api.dto.task;

import com.nexxus.common.enums.cos.task.TaskStatus;
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
public class TaskListItem implements Serializable {
    private Long orgId;
    private String displayId;
    private String title;
    private UserDto assignee;
    private Instant deadline;
    private Integer daysToDeadline;
    private TaskStatus status;
}
