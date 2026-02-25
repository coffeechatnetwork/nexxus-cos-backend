package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.UserEntity;

public interface UserService extends IService<UserEntity> {
    UserEntity getByEmail(String email);
}
