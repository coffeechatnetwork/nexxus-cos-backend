package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.QuestionEntity;
import com.nexxus.cos.service.service.QuestionService;
import com.nexxus.cos.service.service.UserService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionConverter {

    private final UserService userService;
    private final UserConverter userConverter;
    private final FileApi fileApi;
    private final QuestionService questionService;

    public QuestionDto toQuestionDto(QuestionEntity entity) {
        List<UUID> assigneeIds = entity.getAssignees();
        List<UserDto> assignees = List.of();
        if (!CollectionUtils.isEmpty(assigneeIds)) {
            assignees = userService.mapByAccountIds(assigneeIds).values().stream()
                    .map(userConverter::toUserDto)
                    .toList();
        }
        Long followUpId = entity.getFollowUpId();
        if (followUpId != null && followUpId != 0L) {
            questionService.getById(followUpId);
        }

        return QuestionDto.builder()
                .orgId(entity.getOrgId())
                .projectId(entity.getProjectId())
                .displayId(entity.getDisplayId())
                .content(entity.getContent())
                .category(entity.getCategory())
                .priority(entity.getPriority())
                .status(entity.getStatus())
                .assignees(assignees)
                .attachments(fileApi.signAttachments(entity.getAttachments()))
                .responses(null)
                .build();
    }
}
