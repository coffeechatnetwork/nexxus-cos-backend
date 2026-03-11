package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.project.ProjectDto;
import com.nexxus.cos.api.dto.project.ProjectListItem;
import com.nexxus.cos.service.entity.ProjectEntity;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectConverter {

    private final FileApi fileApi;

    public ProjectDto toProjectDto(ProjectEntity entity) {
        return ProjectDto.builder()
                .id(entity.getId())
                .displayId(entity.getDisplayId())
                .orgId(entity.getOrgId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .logoUrl(fileApi.sign(entity.getLogoUrl()))
                .imageUrls(fileApi.batchSign(entity.getImageUrls()))
                .status(entity.getStatus())
                .build();
    }

    public ProjectListItem toProjectListItem(ProjectEntity entity) {
        return ProjectListItem.builder()
                .id(entity.getId())
                .displayId(entity.getDisplayId())
                .orgId(entity.getOrgId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .logoUrl(fileApi.sign(entity.getLogoUrl()))
                .imageUrls(fileApi.batchSign(entity.getImageUrls()))
                .status(entity.getStatus())
                .build();
    }
}
