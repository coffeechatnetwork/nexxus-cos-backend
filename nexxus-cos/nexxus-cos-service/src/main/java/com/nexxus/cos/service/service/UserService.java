package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService extends IService<UserEntity> {
    UserEntity getByEmail(String email);

    UserEntity getByAccountId(UUID accountId);

    UserEntity getByAccountId(String accountIdStr);

    Page<UserEntity> listUsers(Long orgId, Long page, Long pageSize);

    Map<UUID, UserEntity> mapByAccountIds(List<UUID> accountIds);
}
