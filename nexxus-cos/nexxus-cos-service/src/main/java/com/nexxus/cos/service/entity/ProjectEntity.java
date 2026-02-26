package com.nexxus.cos.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.cos.project.ProjectStatus;
import com.nexxus.cos.service.entity.handlers.JsonbStringListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cos_project", autoResultMap = true)
public class ProjectEntity extends BaseEntity {
    private String displayId;
    private Long orgId;
    private String name;
    private String slug;
    private String logoUrl;
    @TableField(value = "image_urls", typeHandler = JsonbStringListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<String> imageUrls;
    private ProjectStatus status;
}
