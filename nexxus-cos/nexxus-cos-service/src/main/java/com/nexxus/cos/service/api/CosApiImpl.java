package com.nexxus.cos.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.cos.project.ProjectStatus;
import com.nexxus.cos.api.CosApi;
import com.nexxus.cos.api.dto.project.CreateProjectRequest;
import com.nexxus.cos.api.dto.project.ProjectDto;
import com.nexxus.cos.api.dto.project.ProjectListItem;
import com.nexxus.cos.service.entity.OrganizationEntity;
import com.nexxus.cos.service.entity.ProjectEntity;
import com.nexxus.cos.service.service.OrganizationService;
import com.nexxus.cos.service.service.ProjectService;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CosApiImpl implements CosApi {
    private final ProjectService projectService;
    private final OrganizationService organizationService;
    private final FileApi fileApi;

    @Override
    public ProjectDto createProject(CreateProjectRequest req) {
        AccountInfo accountInfo = AccountInfoContext.get();
        // check org
        Long orgId = accountInfo.getOrgId();
        OrganizationEntity organizationEntity = organizationService.getById(orgId);
        if (organizationEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("organization not found"));
        }
        // check project
        String name = req.getName();
        ProjectEntity projectEntity = projectService.getByName(name);
        if (projectEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("project already exist"));
        }

        String displayId = UUID.randomUUID().toString();
        ProjectEntity newProject = ProjectEntity.builder()
                .displayId(displayId)
                .orgId(orgId)
                .name(name)
                .slug(req.getSlug())
                .logoUrl(req.getLogoUrl())
                .imageUrls(req.getImageUrls())
                .status(ProjectStatus.ACTIVE)
                .build();
        projectService.save(newProject);

        return ProjectDto.builder()
                .displayId(newProject.getDisplayId())
                .orgId(newProject.getOrgId())
                .name(newProject.getName())
                .slug(newProject.getSlug())
                .logoUrl(newProject.getLogoUrl())
                .imageUrls(newProject.getImageUrls())
                .status(newProject.getStatus())
                .build();
    }

    @Override
    public PageResult<ProjectListItem> listProject(Long page, Long pageSize) {
        AccountInfo accountInfo = AccountInfoContext.get();
        Long orgId = accountInfo.getOrgId();
        Page<ProjectEntity> entityPage = projectService.listProjects(orgId, page, pageSize);

        List<ProjectListItem> dtoList = entityPage.getRecords().stream()
                .parallel()
                .map(entity -> ProjectListItem.builder()
                        .displayId(entity.getDisplayId())
                        .orgId(entity.getOrgId())
                        .name(entity.getName())
                        .slug(entity.getSlug())
                        .logoUrl(entity.getLogoUrl())
                        .imageUrls(fileApi.batchSign(entity.getImageUrls()))
                        .status(entity.getStatus())
                        .build())
                .collect(Collectors.toList());

        return PageResult.<ProjectListItem>builder()
                .records(dtoList)
                .total(entityPage.getTotal())
                .pageSize(entityPage.getSize())
                .page(entityPage.getCurrent())
                .build();
    }
}
