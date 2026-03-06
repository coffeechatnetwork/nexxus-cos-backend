package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.keydate.KeyDateDto;
import com.nexxus.cos.api.dto.keydate.KeyDateListItem;
import com.nexxus.cos.service.entity.KeyDateEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeyDateConverter {

    private final UserService userService;
    private final UserConverter userConverter;

    public KeyDateDto toKeyDateDto(KeyDateEntity entity) {
        UserEntity creator = userService.getByAccountId(entity.getCreatedBy());
        UserEntity updater = userService.getByAccountId(entity.getUpdatedBy());
        return KeyDateDto.builder()
                .orgId(entity.getOrgId())
                .projectId(entity.getProjectId())
                .displayId(entity.getDisplayId())
                .title(entity.getTitle())
                .shortDesc(entity.getShortDesc())
                .longDesc(entity.getLongDesc())
                .category(entity.getCategory())
                .referenceDate(entity.getReferenceDate())
                .attachments(entity.getAttachments())
                .createdBy(userConverter.toUserDto(creator))
                .createdAt(entity.getCreatedAt())
                .updatedBy(userConverter.toUserDto(updater))
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public KeyDateListItem toKeyDateListItem(KeyDateEntity entity) {
        return KeyDateListItem.builder()
                .title(entity.getTitle())
                .shortDesc(entity.getShortDesc())
                .longDesc(entity.getLongDesc())
                .category(entity.getCategory())
                .referenceDate(entity.getReferenceDate())
                .build();
    }
}
