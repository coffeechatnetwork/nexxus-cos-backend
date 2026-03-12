package com.nexxus.cos.api.dto.task;

import com.nexxus.common.vo.Attachment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest implements Serializable {
    @NotBlank
    @Size(min = 1, max = 128)
    private String title;
    @Size(max = 40)
    private String shortDesc;
    private String longDesc;

    @NotNull
    private UUID assignee;
    private Instant deadline;

    private List<Attachment> attachments;
}
