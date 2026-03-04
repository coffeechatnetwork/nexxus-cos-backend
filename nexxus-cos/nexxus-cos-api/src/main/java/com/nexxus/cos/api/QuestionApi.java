package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.question.CreateQuestionRequest;
import com.nexxus.cos.api.dto.question.EditQuestionRequest;
import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.question.QuestionListItem;

public interface QuestionApi {
    QuestionDto createQuestion(CreateQuestionRequest req);

    QuestionDto getByDisplayId(String displayId);

    QuestionDto edit(EditQuestionRequest req);

    PageResult<QuestionListItem> listQuestions(Long projectId, Long page, Long pageSize, String searchQuery);
}
