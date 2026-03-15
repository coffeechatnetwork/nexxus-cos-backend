package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.role.RoleDto;
import com.nexxus.cos.service.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleConverter {

    public RoleDto toRoleDto(RoleEntity entity) {
        return RoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
