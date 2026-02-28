package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.cos.comment.EntityType;
import com.nexxus.common.vo.Attachment;
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
    private String entityId;
    private EntityType entityType;
    private String content;
    private List<Attachment> attachments;
}
