package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.KeyDateEntity;
import com.nexxus.cos.service.mapper.KeyDateMapper;
import com.nexxus.cos.service.service.KeyDateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyDateServiceImpl extends ServiceImpl<KeyDateMapper, KeyDateEntity> implements KeyDateService {

    private final KeyDateMapper keyDateMapper;

    @Override
    public KeyDateEntity getByProjectIdAndTitle(Long projectId, String title) {
        return lambdaQuery()
                .eq(KeyDateEntity::getProjectId, projectId)
                .eq(KeyDateEntity::getTitle, title)
                .one();
    }

    @Override
    public KeyDateEntity getByDisplayId(String displayId) {
        return lambdaQuery()
                .eq(KeyDateEntity::getDisplayId, displayId)
                .one();
    }

    @Override
    public Page<KeyDateEntity> listKeyDates(Long projectId, Long page, Long pageSize, Instant startDate, Instant endDate) {
        Page<KeyDateEntity> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<KeyDateEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(KeyDateEntity::getProjectId, projectId);
        if (Objects.nonNull(startDate)) {
            queryWrapper.ge(KeyDateEntity::getReferenceDate, startDate);
        }
        if (Objects.nonNull(endDate)) {
            queryWrapper.le(KeyDateEntity::getReferenceDate, endDate);
        }
        queryWrapper.orderByDesc(KeyDateEntity::getId);
        return keyDateMapper.selectPage(pageParam, queryWrapper);
    }
}
