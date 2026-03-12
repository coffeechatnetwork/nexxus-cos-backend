package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.TaskEntity;

import java.util.List;
import java.util.Map;

public interface TaskService extends IService<TaskEntity> {
    TaskEntity getByProjectIdAndTitle(Long projectId, String title);

    TaskEntity getByDisplayId(String displayId);

    Page<TaskEntity> listTasks(Long projectId, Long page, Long pageSize);

    Map<String, TaskEntity> mapByDisplayIds(List<String> displayIds);

    List<TaskEntity> getByProjectId(Long projectId);
}
