package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.api.dto.deliverable.DeliverableListItem;
import com.nexxus.cos.api.dto.task.TaskListItem;
import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.entity.TaskEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.TaskService;
import com.nexxus.cos.service.service.UserService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliverableConverter {

    private final UserService userService;
    private final UserConverter userConverter;
    private final FileApi fileApi;
    private final TaskService taskService;
    private final ApplicationContext applicationContext;

    public DeliverableDto toDeliverableDto(DeliverableEntity entity) {
        UserEntity assignee = userService.getByAccountId(entity.getAssignee());
        UserEntity creator = userService.getByAccountId(entity.getCreatedBy());
        UserEntity updater = userService.getByAccountId(entity.getUpdatedBy());
        List<UserDto> participants = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entity.getParticipants())) {
            participants = userService.mapByAccountIds(entity.getParticipants()).values().stream()
                    .map(userConverter::toUserDto)
                    .collect(Collectors.toList());
        }

        List<TaskEntity> taskEntities = taskService.mapByDisplayIds(entity.getRelatedTasks())
                .values().stream().toList();
        List<TaskListItem> relatedTaskItems = new ArrayList<>();
        if (!CollectionUtils.isEmpty(taskEntities)) {
            TaskConverter taskConverter = applicationContext.getBean(TaskConverter.class);
            relatedTaskItems = taskEntities.stream()
                    .map(taskConverter::toTaskListItem)
                    .collect(Collectors.toList());
        }

        return DeliverableDto.builder()
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
                .relatedTasks(relatedTaskItems)
                .createdBy(userConverter.toUserDto(creator))
                .updatedBy(userConverter.toUserDto(updater))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public DeliverableListItem toDeliverableListItem(DeliverableEntity entity) {
        UserEntity assignee = userService.getByAccountId(entity.getAssignee());

        Integer daysToDeadline = null;
        if (entity.getDeadline() != null) {
            long days = Duration.between(Instant.now(), entity.getDeadline()).toDays();
            daysToDeadline = (int) days;
        }

        return DeliverableListItem.builder()
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
