package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.deliverable.DeliverableDto;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.UserService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
        return DeliverableDto.builder()
                .displayId(entity.getDisplayId())
                .title(entity.getTitle())
                .shortDesc(entity.getShortDesc())
                .longDesc(entity.getLongDesc())
                .assignee(userConverter.toUserDto(assignee))
                .deadline(entity.getDeadline())
                .participants(new ArrayList<>())
                .status(entity.getStatus())
                .attachments(fileApi.signAttachments(entity.getAttachments()))
                .createdBy(userConverter.toUserDto(creator))
                .updatedBy(userConverter.toUserDto(updater))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
