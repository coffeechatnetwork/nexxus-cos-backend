package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.comment.CommentDto;
import com.nexxus.cos.service.entity.CommentEntity;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final FileApi fileApi;

    public CommentDto toCommentDto(CommentEntity entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .entityId(entity.getEntityId())
                .entityType(entity.getEntityType())
                .content(entity.getContent())
                .type(entity.getType())
                .attachments(fileApi.signAttachments(entity.getAttachments()))
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedAt())
                .updatedBy(entity.getUpdatedBy())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
