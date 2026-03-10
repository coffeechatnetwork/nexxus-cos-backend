package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.question.QuestionListItem;
import com.nexxus.cos.api.dto.question.ResponseDto;
import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.QuestionEntity;
import com.nexxus.cos.service.entity.QuestionResponseEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.QuestionResponseService;
import com.nexxus.cos.service.service.QuestionService;
import com.nexxus.cos.service.service.UserService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionConverter {

    private final UserService userService;
    private final UserConverter userConverter;
    private final FileApi fileApi;
    private final QuestionService questionService;
    private final QuestionResponseService responseService;

    public QuestionDto toQuestionDto(QuestionEntity entity) {
        List<UUID> assigneeIds = entity.getAssignees();
        List<UserDto> assignees = List.of();
        if (!CollectionUtils.isEmpty(assigneeIds)) {
            assignees = userService.mapByAccountIds(assigneeIds).values().stream()
                    .map(userConverter::toUserDto)
                    .toList();
        }
        Long followUpId = entity.getFollowUpId();
        Map<String, String> followUpQuestion = new HashMap<>();
        if (followUpId != null && followUpId != 0L) {
            QuestionEntity followUpEntity = questionService.getById(followUpId);
            followUpQuestion = Map.of(
                    "id", String.valueOf(followUpEntity.getId()),
                    "displayId", followUpEntity.getDisplayId(),
                    "content", followUpEntity.getContent()
            );
        }

        List<QuestionResponseEntity> responseEntities = responseService.getResponsesByQuestionId(entity.getId());
        List<ResponseDto> responses = responseEntities.stream().map(this::toResponseDto).toList();

        return QuestionDto.builder()
                .orgId(entity.getOrgId())
                .projectId(entity.getProjectId())
                .displayId(entity.getDisplayId())
                .content(entity.getContent())
                .category(entity.getCategory())
                .priority(entity.getPriority())
                .status(entity.getStatus())
                .followUpQuestion(followUpQuestion)
                .assignees(assignees)
                .attachments(fileApi.signAttachments(entity.getAttachments()))
                .responses(responses)
                .build();
    }

    public QuestionListItem toQuestionListItem(QuestionEntity entity) {
        QuestionResponseEntity responseEntity = responseService.getLastestResponse(entity.getId());

        return QuestionListItem.builder()
                .orgId(entity.getOrgId())
                .projectId(entity.getProjectId())
                .content(entity.getContent())
                .priority(entity.getPriority())
                .category(entity.getCategory())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .lastestResponse(toResponseDto(responseEntity))
                .build();
    }

    public ResponseDto toResponseDto(QuestionResponseEntity entity) {
        if (entity == null) {
            return null;
        }
        UserEntity creator = userService.getByAccountId(entity.getCreatedBy());
        return ResponseDto.builder()
                .content(entity.getContent())
                .status(entity.getStatus())
                .createdBy(userConverter.toUserDto(creator))
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
