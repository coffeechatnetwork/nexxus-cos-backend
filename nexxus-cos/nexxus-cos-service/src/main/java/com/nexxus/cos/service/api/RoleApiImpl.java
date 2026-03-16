package com.nexxus.cos.service.api;

import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.enums.cos.role.Role;
import com.nexxus.common.enums.cos.role.RoleStatus;
import com.nexxus.cos.api.RoleApi;
import com.nexxus.cos.api.dto.role.CreateRoleRequest;
import com.nexxus.cos.api.dto.role.RoleDto;
import com.nexxus.cos.service.api.converter.RoleConverter;
import com.nexxus.cos.service.entity.RoleEntity;
import com.nexxus.cos.service.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleApiImpl implements RoleApi {

    private final RoleService roleService;
    private final RoleConverter roleConverter;

    @Override
    public RoleDto createRole(CreateRoleRequest req) {
        Role roleName = req.getName();
        RoleEntity roleEntity = roleService.getByName(roleName);
        if (roleEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("role already exists"));
        }
        RoleEntity newRole = RoleEntity.builder()
                .name(req.getName())
                .description(req.getDescription())
                .status(RoleStatus.ACTIVE)
                .build();
        roleService.save(newRole);
        return roleConverter.toRoleDto(newRole);
    }
}
