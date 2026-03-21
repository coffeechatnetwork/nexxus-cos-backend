package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.UserRoleEntity;
import com.nexxus.cos.service.mapper.UserRoleMapper;
import com.nexxus.cos.service.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {
    @Override
    public UserRoleEntity getByUserAndProject(String accountId, Long projectId) {
        return lambdaQuery()
                .eq(UserRoleEntity::getAccountId, accountId)
                .eq(UserRoleEntity::getProjectId, projectId)
                .one();
    }
}
