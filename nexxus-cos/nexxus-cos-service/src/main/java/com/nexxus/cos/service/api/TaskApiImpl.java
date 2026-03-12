package com.nexxus.cos.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.SectionName;
import com.nexxus.common.enums.cos.task.TaskStatus;
import com.nexxus.cos.api.TaskApi;
import com.nexxus.cos.api.dto.calendar.CalendarDto;
import com.nexxus.cos.api.dto.calendar.CalendarEventDto;
import com.nexxus.cos.api.dto.task.CreateTaskRequest;
import com.nexxus.cos.api.dto.task.EditTaskRequest;
import com.nexxus.cos.api.dto.task.TaskDashboardDto;
import com.nexxus.cos.api.dto.task.TaskDto;
import com.nexxus.cos.api.dto.task.TaskListItem;
import com.nexxus.cos.api.dto.task.TaskSectionDto;
import com.nexxus.cos.service.api.converter.TaskConverter;
import com.nexxus.cos.service.entity.TaskEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.TaskService;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskApiImpl implements TaskApi {

    private final TaskService taskService;
    private final UserService userService;
    private final TaskConverter taskConverter;

    @Override
    public TaskDto createTask(Long projectId, CreateTaskRequest req) {
        // get orgId from context
        AccountInfo accountInfo = AccountInfoContext.get();
        Long orgId = accountInfo.getOrgId();

        TaskEntity taskEntity = taskService.getByProjectIdAndTitle(req.getProjectId(), req.getTitle());
        if (taskEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("task already exist in this project"));
        }

        UUID assigneeId = req.getAssignee();
        UserEntity assignee = userService.getByAccountId(assigneeId);
        if (assignee == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("assignee not found"));
        }

        TaskEntity newTask = TaskEntity.builder()
                .orgId(orgId)
                .projectId(req.getProjectId())
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
    public TaskDto getByDisplayId(Long projectId, String displayId) {
        TaskEntity taskEntity = taskService.getByDisplayId(displayId);
        if (taskEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("[detail]task not found"));
        }
        return taskConverter.toTaskDto(taskEntity);
    }

    @Override
    public TaskDto edit(Long projectId, String displayId, EditTaskRequest req) {
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
    public PageResult<TaskListItem> listTasks(Long projectId, Long page, Long pageSize) {
        Page<TaskEntity> taskEntityPage = taskService.listTasks(projectId, page, pageSize);
        List<TaskListItem> items = taskEntityPage.getRecords().stream()
                .map(taskConverter::toTaskListItem)
                .collect(Collectors.toList());
        return PageResult.<TaskListItem>builder()
                .records(items)
                .total(taskEntityPage.getTotal())
                .pageSize(taskEntityPage.getSize())
                .page(taskEntityPage.getCurrent())
                .build();
    }

    @Override
    public TaskDashboardDto dashboard(Long projectId) {
        List<TaskEntity> taskEntities = taskService.getByProjectId(projectId);

        List<TaskListItem> taskListItems = taskEntities.stream()
                .map(taskConverter::toTaskListItem)
                .toList();

        List<CalendarEventDto<TaskListItem>> calendarEvents = taskListItems.stream()
                .filter(item -> item.getDeadline() != null)
                .map(item -> CalendarEventDto.<TaskListItem>builder()
                        .date(item.getDeadline())
                        .event(item)
                        .build())
                .collect(Collectors.toList());

        CalendarDto<TaskListItem> calendar = CalendarDto.<TaskListItem>builder()
                .events(calendarEvents)
                .build();

        Instant now = Instant.now();
        Map<SectionName, List<TaskListItem>> sectionMap = new HashMap<>();
        sectionMap.put(SectionName.OVERDUE, new ArrayList<>());
        sectionMap.put(SectionName.NEXT_7_DAYS, new ArrayList<>());
        sectionMap.put(SectionName.NEXT_30_DAYS, new ArrayList<>());
        sectionMap.put(SectionName.LATER, new ArrayList<>());

        for (TaskListItem item : taskListItems) {
            if (item.getDeadline() == null) {
                sectionMap.get(SectionName.LATER).add(item);
                continue;
            }
            if (item.getDaysToDeadline() != null) {
                int days = item.getDaysToDeadline();
                if (days < 0) {
                    sectionMap.get(SectionName.OVERDUE).add(item);
                } else if (days <= 7) {
                    sectionMap.get(SectionName.NEXT_7_DAYS).add(item);
                } else if (days <= 30) {
                    sectionMap.get(SectionName.NEXT_30_DAYS).add(item);
                } else {
                    sectionMap.get(SectionName.LATER).add(item);
                }
            } else {
                sectionMap.get(SectionName.LATER).add(item);
            }
        }

        List<TaskSectionDto> sections = sectionMap.entrySet().stream()
                .map(entry -> TaskSectionDto.builder()
                        .sectionName(entry.getKey())
                        .count(entry.getValue().size())
                        .items(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        return TaskDashboardDto.builder()
                .calendar(calendar)
                .sections(sections)
                .build();
    }
}
