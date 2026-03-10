package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.common.enums.cos.checklist.DevChecklistStatus;
import com.nexxus.cos.api.dto.checklist.DevChecklistSummaryDto;
import com.nexxus.cos.service.entity.DevChecklistEntity;
import com.nexxus.cos.service.mapper.DevChecklistMapper;
import com.nexxus.cos.service.service.DevChecklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public List<DevChecklistSummaryDto.CategorySummaryItem> summary(Long projectId) {
        LambdaQueryWrapper<DevChecklistEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DevChecklistEntity::getProjectId, projectId);
        wrapper.isNotNull(DevChecklistEntity::getCategory);
        List<DevChecklistEntity> allChecklists = list(wrapper);

        Map<DevChecklistCategory, List<DevChecklistEntity>> groupedByCategory = allChecklists.stream()
                .collect(Collectors.groupingBy(DevChecklistEntity::getCategory));

        return Stream.of(DevChecklistCategory.values())
                .map(category -> {
                    List<DevChecklistEntity> checklists = groupedByCategory.getOrDefault(category, List.of());
                    Map<DevChecklistStatus, Integer> statusCount = checklists.stream()
                            .collect(Collectors.groupingBy(
                                    DevChecklistEntity::getStatus,
                                    Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                            ));
                    for (DevChecklistStatus status : DevChecklistStatus.values()) {
                        statusCount.putIfAbsent(status, 0);
                    }
                    return DevChecklistSummaryDto.CategorySummaryItem.builder()
                            .category(category)
                            .statusCount(statusCount)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
