package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.cos.comment.CommentType;
import com.nexxus.common.enums.cos.comment.EntityType;
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
public class CommentDto implements Serializable {
    private Long id;
    private String entityId;
    private EntityType entityType;
    private String content;
    private CommentType type;
    private Instant createdBy;
    private Instant createdAt;
    private Instant updatedBy;
    private Instant updatedAt;
}
