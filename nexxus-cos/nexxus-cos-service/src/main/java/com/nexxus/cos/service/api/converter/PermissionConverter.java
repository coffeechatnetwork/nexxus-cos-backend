package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.permission.PermissionDto;
import com.nexxus.cos.service.entity.PermissionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionConverter {

    public PermissionDto toPermissionDto(PermissionEntity entity) {
        return PermissionDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .type(entity.getType())
                .moduleName(entity.getModuleName())
                .moduleCode(entity.getModuleCode())
                .featureName(entity.getFeatureName())
                .featureCode(entity.getFeatureCode())
                .operationCode(entity.getOperationCode())
                .urlPattern(entity.getUrlPattern())
                .httpMethod(entity.getHttpMethod())
                .build();
    }
}
