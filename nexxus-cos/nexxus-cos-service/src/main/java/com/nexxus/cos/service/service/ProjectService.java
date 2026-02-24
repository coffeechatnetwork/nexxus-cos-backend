package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.ProjectEntity;

public interface ProjectService extends IService<ProjectEntity> {
    ProjectEntity getByName(String name);

    Page<ProjectEntity> listProjects(Long page, Long pageSize);
}
