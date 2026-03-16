package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.PermissionEntity;
import com.nexxus.cos.service.mapper.PermissionMapper;
import com.nexxus.cos.service.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {
    @Override
    public PermissionEntity getPermissionByCode(String code) {
        return lambdaQuery().eq(PermissionEntity::getCode, code).one();
    }
}
