package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.comment.EntityType;
import com.nexxus.cos.api.dto.CommentDto;
import com.nexxus.cos.api.dto.CreateCommentRequest;
import com.nexxus.cos.api.dto.EditCommentRequest;

public interface CommentApi {
    CommentDto create(CreateCommentRequest req);

    CommentDto edit(Long commentId, EditCommentRequest req);

    Boolean delete(Long commentId);

    PageResult<CommentDto> listEntityComments(String entityId, EntityType entityType, Long page, Long pageSize);
}
