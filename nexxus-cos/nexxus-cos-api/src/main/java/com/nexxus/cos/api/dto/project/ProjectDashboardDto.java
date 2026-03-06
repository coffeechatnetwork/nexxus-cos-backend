package com.nexxus.cos.api.dto.project;

import com.nexxus.common.enums.cos.project.ProjectStatus;
import com.nexxus.cos.api.dto.deliverable.DeliverableDashboardDto;
import com.nexxus.cos.api.dto.task.TaskDashboardDto;
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
public class ProjectDashboardDto implements Serializable {
    private String displayId;
    private String name;
    private String logoUrl;
    private List<String> imageUrls;
    private ProjectStatus status;
    private DeliverableDashboardDto deliverableDashboard;
    private TaskDashboardDto taskDashboard;
}
