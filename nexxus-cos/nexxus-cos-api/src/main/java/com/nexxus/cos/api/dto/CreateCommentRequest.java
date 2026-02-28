package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.cos.comment.EntityType;
import com.nexxus.common.vo.Attachment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest implements Serializable {
    @NotBlank
    private String entityId;
    @NotNull
    private EntityType entityType;
    @NotNull
    private String content;
    private List<Attachment> attachments;
}
