package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.UserEntity;

public interface UserService extends IService<UserEntity> {
    UserEntity getByEmail(String email);

    UserEntity getByAccountId(String accountId);

    Page<UserEntity> listUsers(Long orgId, Long page, Long pageSize);
}
