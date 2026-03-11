package com.nexxus.server.controller.v1;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.TaskApi;
import com.nexxus.cos.api.dto.task.CreateTaskRequest;
import com.nexxus.cos.api.dto.task.EditTaskRequest;
import com.nexxus.cos.api.dto.task.ListTaskRequest;
import com.nexxus.cos.api.dto.task.TaskDto;
import com.nexxus.cos.api.dto.task.TaskListItem;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/tasks")
public class TaskController {

    private final TaskApi taskApi;

    @PostMapping("")
    public TaskDto createTask(@PathVariable Long projectId, @RequestBody @Valid CreateTaskRequest req) {
        log.info("create task req {}", req);
        return taskApi.createTask(projectId, req);
    }

    @GetMapping("/{displayId}")
    public TaskDto detail(@PathVariable Long projectId, @PathVariable String displayId) {
        return taskApi.getByDisplayId(projectId, displayId);
    }

    @PostMapping("/{displayId}/edit")
    public TaskDto edit(@PathVariable Long projectId, @PathVariable String displayId, @RequestBody @Valid EditTaskRequest req) {
        log.info("edit task req {}", req);
        return taskApi.edit(projectId, displayId, req);
    }

    @PostMapping("/list")
    public PageResult<TaskListItem> list(@PathVariable Long projectId, @RequestBody @Valid ListTaskRequest req) {
        return taskApi.listTasks(projectId, req.getPage(), req.getPageSize());
    }
}
