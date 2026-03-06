package com.nexxus.server.controller.v1;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.CosApi;
import com.nexxus.cos.api.dto.project.CreateProjectRequest;
import com.nexxus.cos.api.dto.project.ListProjectRequest;
import com.nexxus.cos.api.dto.project.ProjectDashboardDto;
import com.nexxus.cos.api.dto.project.ProjectDashboardRequest;
import com.nexxus.cos.api.dto.project.ProjectDto;
import com.nexxus.cos.api.dto.project.ProjectListItem;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final CosApi cosApi;

    @PostMapping("")
    public ProjectDto createProject(@RequestBody @Valid CreateProjectRequest req) {
        log.info("create project req: {}", req);
        return cosApi.createProject(req);
    }

    @PostMapping("/list")
    public PageResult<ProjectListItem> listProject(@RequestBody @Valid ListProjectRequest req) {
        return cosApi.listProject(req.getPage(), req.getPageSize());
    }

    @PostMapping("/dashboard")
    public ProjectDashboardDto getProjectDashboard(@RequestBody @Valid ProjectDashboardRequest req) {
        return cosApi.dashboard(req.getDisplayId());
    }

}
