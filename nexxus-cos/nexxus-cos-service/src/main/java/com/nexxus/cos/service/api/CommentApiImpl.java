package com.nexxus.cos.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.comment.CommentType;
import com.nexxus.common.enums.cos.comment.EntityType;
import com.nexxus.cos.api.CommentApi;
import com.nexxus.cos.api.dto.CommentDto;
import com.nexxus.cos.api.dto.CreateCommentRequest;
import com.nexxus.cos.api.dto.EditCommentRequest;
import com.nexxus.cos.service.entity.CommentEntity;
import com.nexxus.cos.service.service.CommentService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentApiImpl implements CommentApi {

    private final CommentService commentService;
    private final FileApi fileApi;

    @Override
    public CommentDto create(CreateCommentRequest req) {

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
    public CommentDto edit(Long commentId, EditCommentRequest req) {
        CommentEntity commentEntity = commentService.getById(commentId);
        if (commentEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("Comment not found"));
        }

        commentEntity.setContent(req.getContent());
        if (req.getAttachments() != null) {
            commentEntity.setAttachments(req.getAttachments());
        }

        commentService.updateById(commentEntity);

        return CommentDto.builder()
                .id(commentEntity.getId())
                .entityId(commentEntity.getEntityId())
                .entityType(commentEntity.getEntityType())
                .content(commentEntity.getContent())
                .type(commentEntity.getType())
                .attachments(fileApi.signAttachments(commentEntity.getAttachments()))
                .createdBy(commentEntity.getCreatedBy())
                .createdAt(commentEntity.getCreatedAt())
                .updatedBy(commentEntity.getUpdatedBy())
                .updatedAt(commentEntity.getUpdatedAt())
                .build();
    }

    @Override
    public Boolean delete(Long commentId) {
        CommentEntity commentEntity = commentService.getById(commentId);
        if (commentEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("Comment not found"));
        }
        return commentService.removeById(commentEntity);
    }

    @Override
    public PageResult<CommentDto> listEntityComments(String entityId, EntityType entityType, Long page, Long pageSize) {
        Page<CommentEntity> commentEntityPage = commentService.listComments(entityId, entityType, page, pageSize);

        List<CommentDto> commentDtos = commentEntityPage.getRecords().stream()
                .parallel()
                .map(entity -> CommentDto.builder()
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
                        .build())
                .collect(Collectors.toList());

        return PageResult.<CommentDto>builder()
                .records(commentDtos)
                .total(commentEntityPage.getTotal())
                .pageSize(commentEntityPage.getSize())
                .page(commentEntityPage.getCurrent())
                .build();
    }
}
