package com.nexxus.cos.service.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.QuestionApi;
import com.nexxus.cos.api.dto.question.CreateQuestionRequest;
import com.nexxus.cos.api.dto.question.CreateResponseRequest;
import com.nexxus.cos.api.dto.question.EditQuestionRequest;
import com.nexxus.cos.api.dto.question.EditResponseRequest;
import com.nexxus.cos.api.dto.question.PublishResponseRequest;
import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.question.QuestionListItem;
import com.nexxus.cos.api.dto.question.ResponseDto;
import com.nexxus.cos.service.api.converter.QuestionConverter;
import com.nexxus.cos.service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionApiImpl implements QuestionApi {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;

    @Override
    public QuestionDto createQuestion(CreateQuestionRequest req) {
        return null;
    }

    @Override
    public QuestionDto getByDisplayId(String displayId) {
        return null;
    }

    @Override
    public QuestionDto edit(String displayId, EditQuestionRequest req) {
        return null;
    }

    @Override
    public PageResult<QuestionListItem> listQuestions(Long projectId, Long page, Long pageSize, String searchQuery) {
        return null;
    }

    @Override
    public ResponseDto createResponse(CreateResponseRequest req) {
        return null;
    }

    @Override
    public ResponseDto editResponse(EditResponseRequest req) {
        return null;
    }

    @Override
    public ResponseDto publishResponse(PublishResponseRequest req) {
        return null;
    }
}
