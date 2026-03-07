package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.service.query.DeliverableQuery;

import java.util.List;
import java.util.Map;

public interface DeliverableService extends IService<DeliverableEntity> {
    DeliverableEntity getByProjectIdAndTitle(Long projectId, String title);

    DeliverableEntity getByDisplayId(String displayId);

    Page<DeliverableEntity> listDeliverables(DeliverableQuery query);

    Map<String, DeliverableEntity> mapByDisplayIds(List<String> displayIds);
}
