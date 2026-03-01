package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.UserService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeliverableConverter {

    private final UserService userService;
    private final UserConverter userConverter;
    private final FileApi fileApi;

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

        return DeliverableDto.builder()
                .displayId(entity.getDisplayId())
                .title(entity.getTitle())
                .shortDesc(entity.getShortDesc())
                .longDesc(entity.getLongDesc())
                .assignee(userConverter.toUserDto(assignee))
                .deadline(entity.getDeadline())
                .participants(participants)
                .status(entity.getStatus())
                .attachments(fileApi.signAttachments(entity.getAttachments()))
                .createdBy(userConverter.toUserDto(creator))
                .updatedBy(userConverter.toUserDto(updater))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
