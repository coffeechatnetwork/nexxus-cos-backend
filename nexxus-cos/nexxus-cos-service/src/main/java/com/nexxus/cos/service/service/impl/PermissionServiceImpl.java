package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.PermissionEntity;
import com.nexxus.cos.service.entity.UserRoleEntity;
import com.nexxus.cos.service.mapper.PermissionMapper;
import com.nexxus.cos.service.service.PermissionService;
import com.nexxus.cos.service.service.RolePermissionService;
import com.nexxus.cos.service.service.UserRoleService;
import com.nexxus.cos.service.util.UrlPatternMatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    private final UrlPatternMatcher urlPatternMatcher;
    private final UserRoleService userRoleService;
    private final RolePermissionService rolePermissionService;

    @Override
    public PermissionEntity getPermissionByCode(String code) {
        return lambdaQuery().eq(PermissionEntity::getCode, code).one();
    }

    @Override
    public PermissionEntity getPermissionByUrl(String urlPattern, String httpMethod) {
        return lambdaQuery()
                .eq(PermissionEntity::getUrlPattern, urlPattern)
                .eq(PermissionEntity::getHttpMethod, httpMethod)
                .one();
    }

    @Override
    public Boolean hasPermission(String accountId, Long projectId, Long permissionId) {
        UserRoleEntity userRole = userRoleService.getByUserAndProject(accountId, projectId);
        if (userRole == null) {
            log.warn("No role found for account [{}] in project [{}]", accountId, projectId);
            return false;
        }

        return rolePermissionService.hasPermission(userRole.getRoleId(), permissionId);
    }

    @Override
    public Boolean hasPermissionForUrl(String accountId, String requestUrl, String httpMethod) {
        PermissionEntity permission = getPermissionByUrl(requestUrl, httpMethod);
        if (permission == null) {
            log.warn("No permission configured for [{} {}], allowing by default", httpMethod, requestUrl);
            return true;
        }
        Long projectId = urlPatternMatcher.extractProjectId(requestUrl, permission.getUrlPattern());
        if (projectId == null) {
            log.warn("Cannot extract project ID from URL [{}]", requestUrl);
            return false;
        }
        return hasPermission(accountId, projectId, permission.getId());
    }
}
