package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.task.CreateTaskRequest;
import com.nexxus.cos.api.dto.task.EditTaskRequest;
import com.nexxus.cos.api.dto.task.TaskDashboardDto;
import com.nexxus.cos.api.dto.task.TaskDto;
import com.nexxus.cos.api.dto.task.TaskListItem;

public interface TaskApi {
    TaskDto createTask(Long projectId, CreateTaskRequest req);

    TaskDto getByDisplayId(Long projectId, String displayId);

    TaskDto edit(Long projectId, String displayId, EditTaskRequest req);

    PageResult<TaskListItem> listTasks(Long projectId, Long page, Long pageSize);

    TaskDashboardDto dashboard(Long projectId);
}
