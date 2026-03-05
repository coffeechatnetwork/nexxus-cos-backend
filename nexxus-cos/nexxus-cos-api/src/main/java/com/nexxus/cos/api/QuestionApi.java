package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.question.CreateQuestionRequest;
import com.nexxus.cos.api.dto.question.CreateResponseRequest;
import com.nexxus.cos.api.dto.question.EditQuestionRequest;
import com.nexxus.cos.api.dto.question.EditResponseRequest;
import com.nexxus.cos.api.dto.question.PublishResponseRequest;
import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.question.QuestionListItem;
import com.nexxus.cos.api.dto.question.ResponseDto;

public interface QuestionApi {
    QuestionDto createQuestion(CreateQuestionRequest req);

    QuestionDto getByDisplayId(String displayId);

    QuestionDto edit(String displayId, EditQuestionRequest req);

    PageResult<QuestionListItem> listQuestions(Long projectId, Long page, Long pageSize, String searchQuery);

    ResponseDto createResponse(CreateResponseRequest req);

    ResponseDto editResponse(EditResponseRequest req);

    ResponseDto publishResponse(PublishResponseRequest req);
}
