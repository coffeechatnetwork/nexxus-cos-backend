package com.nexxus.auth.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.auth.service.entity.AppEntity;
import com.nexxus.auth.service.mapper.AppMapper;
import com.nexxus.auth.service.service.AppService;
import com.nexxus.common.enums.auth.AppCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppServiceImpl extends ServiceImpl<AppMapper, AppEntity> implements AppService {
    @Override
    public AppEntity getByCode(AppCode appCode) {
        return lambdaQuery()
                .eq(AppEntity::getCode, appCode)
                .one();
    }
}
