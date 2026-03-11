package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.project.CreateProjectRequest;
import com.nexxus.cos.api.dto.project.ProjectDashboardDto;
import com.nexxus.cos.api.dto.project.ProjectDto;
import com.nexxus.cos.api.dto.project.ProjectListItem;

public interface CosApi {
    ProjectDto createProject(CreateProjectRequest req);

    PageResult<ProjectListItem> listProject(Long page, Long pageSize);

    ProjectDashboardDto dashboard(Long projectId);
}
