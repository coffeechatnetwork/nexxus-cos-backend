package com.nexxus.cos.api.dto.task;

import com.nexxus.common.enums.cos.task.TaskStatus;
import com.nexxus.common.vo.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EditTaskRequest implements Serializable {
    private List<Attachment> attachments;
    private List<UUID> participants;
    private TaskStatus status;
    private List<String> relatedDeliverables;
}
