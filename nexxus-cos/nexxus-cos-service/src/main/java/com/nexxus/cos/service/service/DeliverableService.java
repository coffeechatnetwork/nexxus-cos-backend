package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.DeliverableEntity;

public interface DeliverableService extends IService<DeliverableEntity> {
    DeliverableEntity getByTitle(String title);
}
