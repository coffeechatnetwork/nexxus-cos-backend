package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.cos.service.entity.DevChecklistEntity;
import com.nexxus.cos.service.mapper.DevChecklistMapper;
import com.nexxus.cos.service.service.DevChecklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DevChecklistServiceImpl extends ServiceImpl<DevChecklistMapper, DevChecklistEntity> implements DevChecklistService {
    @Override
    public DevChecklistEntity getByProjectIdAndTitle(Long projectId, String title) {
        LambdaQueryWrapper<DevChecklistEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DevChecklistEntity::getProjectId, projectId)
                .eq(DevChecklistEntity::getTitle, title);
        return getOne(wrapper);
    }

    @Override
    public DevChecklistEntity getByDisplayId(String displayId) {
        LambdaQueryWrapper<DevChecklistEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DevChecklistEntity::getDisplayId, displayId);
        return getOne(wrapper);
    }

    @Override
    public Page<DevChecklistEntity> listDevChecklists(Long projectId, Long page, Long pageSize, DevChecklistCategory category) {
        LambdaQueryWrapper<DevChecklistEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DevChecklistEntity::getProjectId, projectId);
        if (category != null) {
            wrapper.eq(DevChecklistEntity::getCategory, category);
        }
        wrapper.orderByAsc(DevChecklistEntity::getTitle);
        return page(new Page<>(page, pageSize), wrapper);
    }
}
