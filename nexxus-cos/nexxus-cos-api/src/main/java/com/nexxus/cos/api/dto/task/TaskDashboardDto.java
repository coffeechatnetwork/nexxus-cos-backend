package com.nexxus.cos.api.dto.task;

import com.nexxus.cos.api.dto.calendar.CalendarDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDashboardDto implements Serializable {
    private CalendarDto<TaskListItem> calendar;
    private List<TaskSectionDto> sections;
}
