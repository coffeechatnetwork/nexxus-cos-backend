package com.nexxus.cos.service.api;

import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.cos.api.PermissionApi;
import com.nexxus.cos.api.dto.permission.CreatePermissionRequest;
import com.nexxus.cos.api.dto.permission.PermissionDto;
import com.nexxus.cos.service.api.converter.PermissionConverter;
import com.nexxus.cos.service.entity.PermissionEntity;
import com.nexxus.cos.service.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionApiImpl implements PermissionApi {

    private final PermissionService permissionService;
    private final PermissionConverter permissionConverter;

    @Override
    public PermissionDto createPermission(CreatePermissionRequest req) {
        String code = req.getCode();
        PermissionEntity permissionEntity = permissionService.getPermissionByCode(code);
        if (permissionEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("this permission already exist"));
        }
        PermissionEntity newPermission = PermissionEntity.builder()
                .code(req.getCode())
                .type(req.getType())
                .moduleName(req.getModuleName())
                .moduleCode(req.getModuleCode())
                .featureName(req.getFeatureName())
                .featureCode(req.getFeatureCode())
                .operationCode(req.getOperationCode())
                .urlPattern(req.getUrlPattern())
                .httpMethod(req.getHttpMethod())
                .build();
        permissionService.save(newPermission);
        return permissionConverter.toPermissionDto(newPermission);
    }
}
