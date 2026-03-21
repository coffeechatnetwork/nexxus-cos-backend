package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.RolePermissionEntity;
import com.nexxus.cos.service.mapper.RolePermissionMapper;
import com.nexxus.cos.service.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity> implements RolePermissionService {
    @Override
    public Boolean hasPermission(Long roleId, Long permissionId) {
        return lambdaQuery()
                .eq(RolePermissionEntity::getRoleId, roleId)
                .eq(RolePermissionEntity::getPermissionId, permissionId)
                .exists();
    }
}
