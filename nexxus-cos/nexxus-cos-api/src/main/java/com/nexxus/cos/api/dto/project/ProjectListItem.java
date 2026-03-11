package com.nexxus.cos.api.dto.project;

import com.nexxus.common.enums.cos.project.ProjectStatus;
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
public class ProjectListItem implements Serializable {
    private Long id;
    private String displayId;
    private Long orgId;
    private String name;
    private String slug;
    private String logoUrl;
    private List<String> imageUrls;
    private ProjectStatus status;
}
