package com.nexxus.auth.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.auth.service.entity.AppEntity;
import com.nexxus.common.enums.auth.AppCode;

public interface AppService extends IService<AppEntity> {
    AppEntity getByCode(AppCode appCode);
}
