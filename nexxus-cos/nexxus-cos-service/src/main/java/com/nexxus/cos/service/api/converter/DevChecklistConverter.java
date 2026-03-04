package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.checklist.DevChecklistDto;
import com.nexxus.cos.api.dto.checklist.DevChecklistListItem;
import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.DevChecklistEntity;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DevChecklistConverter {

    private final FileApi fileApi;
    private final UserApi userApi;

    public DevChecklistDto toDevChecklistDto(DevChecklistEntity entity) {
        return DevChecklistDto.builder()
                .orgId(entity.getOrgId())
                .projectId(entity.getProjectId())
                .displayId(entity.getDisplayId())
                .name(entity.getTitle())
                .category(entity.getCategory())
                .status(entity.getStatus())
                .waitingOn(entity.getWaitingOn())
                .attachments(fileApi.signAttachments(entity.getAttachments()))
                .build();
    }

    public DevChecklistListItem toDevChecklistListItem(DevChecklistEntity entity) {
        UserDto creator = userApi.getUserByAccountId(entity.getCreatedBy());
        UserDto updater = userApi.getUserByAccountId(entity.getUpdatedBy());
        return DevChecklistListItem.builder()
                .displayId(entity.getDisplayId())
                .name(entity.getTitle())
                .status(entity.getStatus())
                .category(entity.getCategory())
                .waitingOn(entity.getWaitingOn())
                .createdBy(creator)
                .createdAt(entity.getCreatedAt())
                .updatedBy(updater)
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
