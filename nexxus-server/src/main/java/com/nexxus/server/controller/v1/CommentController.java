package com.nexxus.server.controller.v1;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.CommentApi;
import com.nexxus.cos.api.dto.CommentDto;
import com.nexxus.cos.api.dto.CreateCommentRequest;
import com.nexxus.cos.api.dto.ListCommentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentApi commentApi;

    @PostMapping("")
    public CommentDto create(@RequestBody @Valid CreateCommentRequest req) {
        return commentApi.create(req);
    }

    @PostMapping("/list")
    public PageResult<CommentDto> list(@RequestBody @Valid ListCommentRequest req) {
        return commentApi.listEntityComments(
                req.getEntityId(),
                req.getEntityType(),
                req.getPage(),
                req.getPageSize());
    }
}
