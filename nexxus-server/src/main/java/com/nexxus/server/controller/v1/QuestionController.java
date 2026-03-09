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
@RequestMapping("/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionApi questionApi;

    @PostMapping("")
    public QuestionDto createQuestion(@RequestBody @Valid CreateQuestionRequest req) {
        return questionApi.createQuestion(req);
    }

    @GetMapping("/{displayId}")
    public QuestionDto getByDisplayId(@PathVariable String displayId) {
        return questionApi.getByDisplayId(displayId);
    }

    @PostMapping("/{displayId}/edit")
    public QuestionDto edit(@PathVariable String displayId, @RequestBody @Valid EditQuestionRequest req) {
        return questionApi.edit(displayId, req);
    }

    @PostMapping("/list")
    public PageResult<QuestionListItem> listQuestions(@RequestBody @Valid ListQuestionRequest req) {
        return questionApi.listQuestions(req.getProjectId(), req.getPage(), req.getPageSize(), req.getSearchQuery());
    }

    @PostMapping("/responses")
    public ResponseDto createResponse(@RequestBody @Valid CreateResponseRequest req) {
        return questionApi.createResponse(req);
    }

    @PostMapping("/responses/edit")
    public ResponseDto editResponse(@RequestBody @Valid EditResponseRequest req) {
        return questionApi.editResponse(req);
    }

    @PostMapping("/responses/publish")
    public ResponseDto publishResponse(@RequestBody @Valid PublishResponseRequest req) {
        return questionApi.publishResponse(req);
    }

    @PostMapping("/summary")
    public QuestionSummaryDto summary(@RequestBody @Valid QuestionSummaryRequest req) {
        return questionApi.summary(req);
    }
}
