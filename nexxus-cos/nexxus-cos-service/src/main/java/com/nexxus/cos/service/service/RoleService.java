package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.RoleEntity;

public interface RoleService extends IService<RoleEntity> {
    RoleEntity getByName(String name);
}
