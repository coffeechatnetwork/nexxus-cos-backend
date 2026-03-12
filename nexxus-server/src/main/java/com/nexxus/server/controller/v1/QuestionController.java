package com.nexxus.server.controller.v1;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.QuestionApi;
import com.nexxus.cos.api.dto.question.CreateQuestionRequest;
import com.nexxus.cos.api.dto.question.CreateResponseRequest;
import com.nexxus.cos.api.dto.question.EditQuestionRequest;
import com.nexxus.cos.api.dto.question.EditResponseRequest;
import com.nexxus.cos.api.dto.question.ListQuestionRequest;
import com.nexxus.cos.api.dto.question.PublishResponseRequest;
import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.question.QuestionListItem;
import com.nexxus.cos.api.dto.question.QuestionSummaryDto;
import com.nexxus.cos.api.dto.question.QuestionSummaryRequest;
import com.nexxus.cos.api.dto.question.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects/{projectId}/questions")
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionApi questionApi;

    @PostMapping("")
    public QuestionDto createQuestion(@PathVariable Long projectId, @RequestBody @Valid CreateQuestionRequest req) {
        return questionApi.createQuestion(projectId, req);
    }

    @GetMapping("/{displayId}")
    public QuestionDto getByDisplayId(@PathVariable Long projectId, @PathVariable String displayId) {
        return questionApi.getByDisplayId(projectId, displayId);
    }

    @PostMapping("/{displayId}/edit")
    public QuestionDto edit(@PathVariable Long projectId, @PathVariable String displayId, @RequestBody @Valid EditQuestionRequest req) {
        return questionApi.edit(projectId, displayId, req);
    }

    @PostMapping("/list")
    public PageResult<QuestionListItem> listQuestions(@PathVariable Long projectId, @RequestBody @Valid ListQuestionRequest req) {
        return questionApi.listQuestions(projectId, req.getPage(), req.getPageSize(), req.getSearchQuery());
    }

    @PostMapping("/responses")
    public ResponseDto createResponse(@PathVariable Long projectId, @RequestBody @Valid CreateResponseRequest req) {
        return questionApi.createResponse(projectId, req);
    }

    @PostMapping("/responses/edit")
    public ResponseDto editResponse(@PathVariable Long projectId, @RequestBody @Valid EditResponseRequest req) {
        return questionApi.editResponse(projectId, req);
    }

    @PostMapping("/responses/publish")
    public ResponseDto publishResponse(@PathVariable Long projectId, @RequestBody @Valid PublishResponseRequest req) {
        return questionApi.publishResponse(projectId, req);
    }

    @PostMapping("/summary")
    public QuestionSummaryDto summary(@PathVariable Long projectId, @RequestBody @Valid QuestionSummaryRequest req) {
        return questionApi.summary(projectId, req);
    }
}
