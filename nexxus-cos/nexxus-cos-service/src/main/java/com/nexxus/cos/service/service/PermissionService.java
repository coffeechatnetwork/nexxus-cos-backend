package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.PermissionEntity;

public interface PermissionService extends IService<PermissionEntity> {
    PermissionEntity getPermissionByCode(String code);

    PermissionEntity getPermissionByUrl(String urlPattern, String httpMethod);

    Boolean hasPermission(String accountId, Long projectId, Long permissionId);

    Boolean hasPermissionForUrl(String accountId, String urlPattern, String httpMethod);
}
