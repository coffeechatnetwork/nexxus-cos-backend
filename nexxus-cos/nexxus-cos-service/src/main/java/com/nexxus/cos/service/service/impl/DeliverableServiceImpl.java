package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.DeliverableEntity;
import com.nexxus.cos.service.mapper.DeliverableMapper;
import com.nexxus.cos.service.service.DeliverableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliverableServiceImpl extends ServiceImpl<DeliverableMapper, DeliverableEntity> implements DeliverableService {
    @Override
    public DeliverableEntity getByTitle(String title) {
        return lambdaQuery()
                .eq(DeliverableEntity::getTitle, title)
                .one();
    }
}
