package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.question.CreateQuestionRequest;
import com.nexxus.cos.api.dto.question.CreateResponseRequest;
import com.nexxus.cos.api.dto.question.EditQuestionRequest;
import com.nexxus.cos.api.dto.question.EditResponseRequest;
import com.nexxus.cos.api.dto.question.PublishResponseRequest;
import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.question.QuestionListItem;
import com.nexxus.cos.api.dto.question.QuestionSummaryDto;
import com.nexxus.cos.api.dto.question.QuestionSummaryRequest;
import com.nexxus.cos.api.dto.question.ResponseDto;

public interface QuestionApi {
    QuestionDto createQuestion(Long projectId, CreateQuestionRequest req);

    QuestionDto getByDisplayId(Long projectId, String displayId);

    QuestionDto edit(Long projectId, String displayId, EditQuestionRequest req);

    PageResult<QuestionListItem> listQuestions(Long projectId, Long page, Long pageSize, String searchQuery);

    ResponseDto createResponse(Long projectId, CreateResponseRequest req);

    ResponseDto editResponse(Long projectId, EditResponseRequest req);

    ResponseDto publishResponse(Long projectId, PublishResponseRequest req);

    QuestionSummaryDto summary(Long projectId, QuestionSummaryRequest req);
}
