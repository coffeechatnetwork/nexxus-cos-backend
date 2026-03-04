package com.nexxus.cos.service.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.QuestionApi;
import com.nexxus.cos.api.dto.question.CreateQuestionRequest;
import com.nexxus.cos.api.dto.question.EditQuestionRequest;
import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.question.QuestionListItem;
import com.nexxus.cos.service.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionApiImpl implements QuestionApi {

    private final QuestionService questionService;

    @Override
    public QuestionDto createQuestion(CreateQuestionRequest req) {
        return null;
    }

    @Override
    public QuestionDto getByDisplayId(String displayId) {
        return null;
    }

    @Override
    public QuestionDto edit(EditQuestionRequest req) {
        return null;
    }

    @Override
    public PageResult<QuestionListItem> listQuestions(Long projectId, Long page, Long pageSize, String searchQuery) {
        return null;
    }
}
