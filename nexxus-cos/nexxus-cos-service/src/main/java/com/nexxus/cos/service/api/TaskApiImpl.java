package com.nexxus.cos.service.api;

import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.task.TaskStatus;
import com.nexxus.cos.api.TaskApi;
import com.nexxus.cos.api.dto.task.CreateTaskRequest;
import com.nexxus.cos.api.dto.task.EditTaskRequest;
import com.nexxus.cos.api.dto.task.TaskDto;
import com.nexxus.cos.api.dto.task.TaskListItem;
import com.nexxus.cos.service.api.converter.TaskConverter;
import com.nexxus.cos.service.entity.TaskEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.TaskService;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskApiImpl implements TaskApi {

    private final TaskService taskService;
    private final UserService userService;
    private final TaskConverter taskConverter;

    @Override
    public TaskDto createTask(CreateTaskRequest req) {
        TaskEntity taskEntity = taskService.getByTitle(req.getTitle());
        if (taskEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("task already exist"));
        }

        UUID assigneeId = req.getAssignee();
        UserEntity assignee = userService.getByAccountId(assigneeId);
        if (assignee == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("assignee not found"));
        }

        TaskEntity newTask = TaskEntity.builder()
                .displayId(UUID.randomUUID().toString())
                .title(req.getTitle())
                .shortDesc(req.getShortDesc())
                .longDesc(req.getLongDesc())
                .assignee(assigneeId)
                .deadline(req.getDeadline())
                .status(TaskStatus.NOT_YET_STARTED)
                .attachments(req.getAttachments())
                .build();
        taskService.save(newTask);
        return taskConverter.toTaskDto(newTask);
    }

    @Override
    public TaskDto getByDisplayId(String displayId) {
        TaskEntity taskEntity = taskService.getByDisplayId(displayId);
        if (taskEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("[detail]task not found"));
        }
        return taskConverter.toTaskDto(taskEntity);
    }

    @Override
    public TaskDto edit(String displayId, EditTaskRequest req) {
        TaskEntity taskEntity = taskService.getByDisplayId(displayId);
        if (taskEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("[edit]task not found"));
        }
        taskEntity.setAttachments(req.getAttachments());
        taskEntity.setParticipants(req.getParticipants());
        taskEntity.setStatus(req.getStatus());
        taskEntity.setRelatedDeliverables(req.getRelatedDeliverables());

        taskService.updateById(taskEntity);
        return taskConverter.toTaskDto(taskEntity);
    }

    @Override
    public PageResult<TaskListItem> listTasks(Long page, Long pageSize) {
        return null;
    }
}
