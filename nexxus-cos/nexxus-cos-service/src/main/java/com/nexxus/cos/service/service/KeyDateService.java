package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.KeyDateEntity;
import com.nexxus.cos.service.service.query.KeyDateQuery;

public interface KeyDateService extends IService<KeyDateEntity> {

    KeyDateEntity getByProjectIdAndTitle(Long projectId, String title);

    KeyDateEntity getByDisplayId(String displayId);

    Page<KeyDateEntity> listKeyDates(KeyDateQuery query);
}
