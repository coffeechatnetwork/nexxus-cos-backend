package com.nexxus.cos.service.api;

import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.comment.EntityType;
import com.nexxus.cos.api.CommentApi;
import com.nexxus.cos.api.dto.CommentDto;
import com.nexxus.cos.api.dto.CreateCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentApiImpl implements CommentApi {
    @Override
    public CommentDto create(CreateCommentRequest req) {
        return null;
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
