package com.nexxus.cos.service.api;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.question.QuestionStatus;
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
import com.nexxus.cos.service.entity.QuestionEntity;
import com.nexxus.cos.service.service.QuestionService;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionApiImpl implements QuestionApi {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final UserService userService;

    @Override
    public QuestionDto createQuestion(CreateQuestionRequest req) {
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
                .projectId(req.getProjectId())
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
