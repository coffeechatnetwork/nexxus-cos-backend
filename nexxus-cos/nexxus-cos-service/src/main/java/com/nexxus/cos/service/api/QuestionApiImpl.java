package com.nexxus.cos.service.api;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.question.QuestionStatus;
import com.nexxus.common.enums.cos.question.ResponseStatus;
import com.nexxus.cos.api.QuestionApi;
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
import com.nexxus.cos.service.api.converter.QuestionConverter;
import com.nexxus.cos.service.entity.QuestionEntity;
import com.nexxus.cos.service.entity.QuestionResponseEntity;
import com.nexxus.cos.service.service.QuestionResponseService;
import com.nexxus.cos.service.service.QuestionService;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionApiImpl implements QuestionApi {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final UserService userService;
    private final QuestionResponseService responseService;

    @Override
    public QuestionDto createQuestion(Long projectId, CreateQuestionRequest req) {
        Long followUpId = req.getFollowUpId();
        if (followUpId != null) {
            QuestionEntity followUpQuestion = questionService.getById(followUpId);
            if (followUpQuestion == null) {
                throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("follow up question not exist"));
            }
        }
        List<UUID> assigneeIds = req.getAssignees();
        List<UUID> notFoundIds = userService.mapByAccountIds(assigneeIds).entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .map(Map.Entry::getKey)
                .toList();
        if (!notFoundIds.isEmpty()) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("assignees not found: " + notFoundIds));
        }
        AccountInfo accountInfo = AccountInfoContext.get();
        QuestionEntity newQuestionEntity = QuestionEntity.builder()
                .orgId(accountInfo.getOrgId())
                .projectId(projectId)
                .displayId(UUID.randomUUID().toString())
                .content(req.getContent())
                .priority(req.getPriority())
                .category(req.getCategory())
                .followUpId(followUpId)
                .status(QuestionStatus.NOT_YET_STARTED)
                .assignees(assigneeIds)
                .build();
        questionService.save(newQuestionEntity);
        return questionConverter.toQuestionDto(newQuestionEntity);
    }

    @Override
    public QuestionDto getByDisplayId(Long projectId, String displayId) {
        QuestionEntity question = questionService.getByDisplayId(displayId);
        if (question == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("question not found"));
        }
        return questionConverter.toQuestionDto(question);
    }

    @Override
    public QuestionDto edit(Long projectId, String displayId, EditQuestionRequest req) {
        QuestionEntity question = questionService.getByDisplayId(displayId);
        if (question == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("question not found"));
        }
        question.setPriority(req.getPriority());
        question.setCategory(req.getCategory());
        question.setStatus(req.getStatus());
        question.setAssignees(req.getAssignees());
        question.setAttachments(req.getAttachments());
        questionService.updateById(question);
        return questionConverter.toQuestionDto(question);
    }

    @Override
    public PageResult<QuestionListItem> listQuestions(Long projectId, Long page, Long pageSize, String searchQuery) {
        var pageResult = questionService.listDevChecklists(projectId, page, pageSize, searchQuery);
        List<QuestionListItem> items = pageResult.getRecords().stream()
                .map(questionConverter::toQuestionListItem)
                .collect(Collectors.toList());
        return PageResult.<QuestionListItem>builder()
                .records(items)
                .total(pageResult.getTotal())
                .pageSize(pageResult.getSize())
                .page(pageResult.getCurrent())
                .build();
    }

    @Override
    public ResponseDto createResponse(Long projectId, CreateResponseRequest req) {
        Long questionId = req.getQuestionId();
        QuestionEntity question = questionService.getById(questionId);
        if (question == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("question not found"));
        }

        QuestionResponseEntity responseEntity = QuestionResponseEntity.builder()
                .orgId(question.getOrgId())
                .questionId(questionId)
                .content(req.getContent())
                .status(req.getStatus())
                .build();
        responseService.save(responseEntity);
        return questionConverter.toResponseDto(responseEntity);
    }

    @Override
    public ResponseDto editResponse(Long projectId, EditResponseRequest req) {
        QuestionResponseEntity response = responseService.getById(req.getResponseId());
        if (response == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("response not found"));
        }
        response.setContent(req.getContent());
        responseService.updateById(response);
        return questionConverter.toResponseDto(response);
    }

    @Override
    public ResponseDto publishResponse(Long projectId, PublishResponseRequest req) {
        QuestionResponseEntity response = responseService.getById(req.getResponseId());
        if (response == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("response not found"));
        }
        response.setStatus(ResponseStatus.PUBLISHED);
        responseService.updateById(response);
        return questionConverter.toResponseDto(response);
    }

    @Override
    public QuestionSummaryDto summary(Long projectId, QuestionSummaryRequest req) {
        var priorities = questionService.summary(req.getProjectId());
        return QuestionSummaryDto.builder()
                .priorities(priorities)
                .build();
    }
}
