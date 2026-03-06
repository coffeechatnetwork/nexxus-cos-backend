package com.nexxus.cos.api.dto.task;

import com.nexxus.common.enums.cos.SectionName;
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
public class TaskSectionDto implements Serializable {
    private SectionName sectionName;
    private Integer count;
    private List<TaskListItem> items;
}
