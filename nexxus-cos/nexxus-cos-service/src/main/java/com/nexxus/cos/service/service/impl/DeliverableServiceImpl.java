package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.mapper.DeliverableMapper;
import com.nexxus.cos.service.service.DeliverableService;
import com.nexxus.cos.service.service.query.DeliverableQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliverableServiceImpl extends ServiceImpl<DeliverableMapper, DeliverableEntity> implements DeliverableService {

    private final DeliverableMapper deliverableMapper;

    @Override
    public DeliverableEntity getByProjectIdAndTitle(Long projectId, String title) {
        return lambdaQuery()
                .eq(DeliverableEntity::getProjectId, projectId)
                .eq(DeliverableEntity::getTitle, title)
                .one();
    }

    @Override
    public DeliverableEntity getByDisplayId(String displayId) {
        return lambdaQuery()
                .eq(DeliverableEntity::getDisplayId, displayId)
                .one();
    }

    @Override
    public Page<DeliverableEntity> listDeliverables(DeliverableQuery query) {
        return lambdaQuery()
                .eq(DeliverableEntity::getProjectId, query.getProjectId())
                .ge(query.getStartDate() != null, DeliverableEntity::getDeadline, query.getStartDate())
                .le(query.getEndDate() != null, DeliverableEntity::getDeadline, query.getEndDate())
                .eq(query.getStatus() != null, DeliverableEntity::getStatus, query.getStatus())
                .orderByDesc(DeliverableEntity::getId)
                .page(new Page<>(query.getPage(), query.getPageSize()));
    }

    @Override
    public Map<String, DeliverableEntity> mapByDisplayIds(List<String> displayIds) {
        if (CollectionUtils.isEmpty(displayIds)) {
            return Map.of();
        }
        List<DeliverableEntity> deliverableEntities = lambdaQuery()
                .in(DeliverableEntity::getDisplayId, displayIds)
                .list();
        return deliverableEntities.stream()
                .collect(Collectors.toMap(DeliverableEntity::getDisplayId,
                        deliverableEntity -> deliverableEntity));
    }
}
