package com.nexxus.cos.service.api;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.comment.CommentType;
import com.nexxus.common.enums.cos.comment.EntityType;
import com.nexxus.cos.api.CommentApi;
import com.nexxus.cos.api.dto.CommentDto;
import com.nexxus.cos.api.dto.CreateCommentRequest;
import com.nexxus.cos.service.entity.CommentEntity;
import com.nexxus.cos.service.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentApiImpl implements CommentApi {

    private final CommentService commentService;

    @Override
    public CommentDto create(CreateCommentRequest req) {
        AccountInfo accountInfo = AccountInfoContext.get();

        CommentEntity commentEntity = CommentEntity.builder()
                .entityId(req.getEntityId())
                .entityType(req.getEntityType())
                .content(req.getContent())
                .type(CommentType.COMMENT)
                .attachments(req.getAttachments())
                .build();

        commentService.save(commentEntity);

        return CommentDto.builder()
                .id(commentEntity.getId())
                .entityId(commentEntity.getEntityId())
                .entityType(commentEntity.getEntityType())
                .content(commentEntity.getContent())
                .type(commentEntity.getType())
                .attachments(commentEntity.getAttachments())
                .createdBy(commentEntity.getCreatedBy())
                .createdAt(commentEntity.getCreatedAt())
                .updatedBy(commentEntity.getUpdatedBy())
                .updatedAt(commentEntity.getUpdatedAt())
                .build();
    }

    @Override
    public CommentDto edit(CreateCommentRequest req) {
        return null;
    }

    @Override
    public Boolean delete(Long commentId) {
        return null;
    }

    @Override
    public PageResult<CommentDto> listEntityComments(String entityId, EntityType entityType, Long page, Long pageSize) {
        return null;
    }
}
