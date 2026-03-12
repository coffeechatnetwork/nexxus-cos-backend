package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.TaskEntity;
import com.nexxus.cos.service.mapper.TaskMapper;
import com.nexxus.cos.service.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskService {

    private final TaskMapper taskMapper;

    @Override
    public TaskEntity getByProjectIdAndTitle(Long projectId, String title) {
        return lambdaQuery()
                .eq(TaskEntity::getProjectId, projectId)
                .eq(TaskEntity::getTitle, title)
                .one();
    }

    @Override
    public TaskEntity getByDisplayId(String displayId) {
        return lambdaQuery().eq(TaskEntity::getDisplayId, displayId).one();
    }

    @Override
    public Page<TaskEntity> listTasks(Long projectId, Long page, Long pageSize) {
        Page<TaskEntity> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<TaskEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskEntity::getProjectId, projectId);
        queryWrapper.orderByDesc(TaskEntity::getId);
        return taskMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public Map<String, TaskEntity> mapByDisplayIds(List<String> displayIds) {
        if (CollectionUtils.isEmpty(displayIds)) {
            return Map.of();
        }

        List<TaskEntity> taskEntities = lambdaQuery()
                .in(TaskEntity::getDisplayId, displayIds)
                .list();
        return taskEntities.stream()
                .collect(Collectors.toMap(TaskEntity::getDisplayId, task -> task));
    }

    @Override
    public List<TaskEntity> getByProjectId(Long projectId) {
        return lambdaQuery()
                .eq(TaskEntity::getProjectId, projectId)
                .list();
    }
}
