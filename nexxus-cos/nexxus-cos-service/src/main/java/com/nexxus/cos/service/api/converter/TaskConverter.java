package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.deliverable.DeliverableListItem;
import com.nexxus.cos.api.dto.task.TaskDto;
import com.nexxus.cos.api.dto.task.TaskListItem;
import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.entity.TaskEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.DeliverableService;
import com.nexxus.cos.service.service.UserService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    private final UserService userService;
    private final UserConverter userConverter;
    private final FileApi fileApi;
    private final DeliverableService deliverableService;
    private final DeliverableConverter deliverableConverter;

    public TaskDto toTaskDto(TaskEntity entity) {
        UserEntity assignee = userService.getByAccountId(entity.getAssignee());
        UserEntity creator = userService.getByAccountId(entity.getCreatedBy());
        UserEntity updater = userService.getByAccountId(entity.getUpdatedBy());
        List<UserDto> participants = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entity.getParticipants())) {
            participants = userService.mapByAccountIds(entity.getParticipants()).values().stream()
                    .map(userConverter::toUserDto)
                    .collect(Collectors.toList());
        }
        List<DeliverableEntity> relatedDeliverables = deliverableService.mapByDisplayIds(entity.getRelatedDeliverables())
                .values().stream().toList();
        List<DeliverableListItem> relatedDeliverableItems = relatedDeliverables.stream()
                .map(deliverableConverter::toDeliverableListItem)
                .toList();

        return TaskDto.builder()
                .orgId(entity.getOrgId())
                .displayId(entity.getDisplayId())
                .title(entity.getTitle())
                .shortDesc(entity.getShortDesc())
                .longDesc(entity.getLongDesc())
                .assignee(userConverter.toUserDto(assignee))
                .deadline(entity.getDeadline())
                .participants(participants)
                .status(entity.getStatus())
                .attachments(fileApi.signAttachments(entity.getAttachments()))
                .relatedDeliverables(relatedDeliverableItems)
                .createdBy(userConverter.toUserDto(creator))
                .createdAt(entity.getCreatedAt())
                .updatedBy(userConverter.toUserDto(updater))
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public TaskListItem toTaskListItem(TaskEntity entity) {
        UserEntity assignee = userService.getByAccountId(entity.getAssignee());

        Integer daysToDeadline = null;
        if (entity.getDeadline() != null) {
            long days = Duration.between(Instant.now(), entity.getDeadline()).toDays();
            daysToDeadline = (int) days;
        }
        return TaskListItem.builder()
                .orgId(entity.getOrgId())
                .displayId(entity.getDisplayId())
                .title(entity.getTitle())
                .assignee(userConverter.toUserDto(assignee))
                .deadline(entity.getDeadline())
                .daysToDeadline(daysToDeadline)
                .status(entity.getStatus())
                .build();
    }
}
