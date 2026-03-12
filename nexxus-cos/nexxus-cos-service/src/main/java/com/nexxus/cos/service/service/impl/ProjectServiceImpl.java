package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.ProjectEntity;
import com.nexxus.cos.service.mapper.ProjectMapper;
import com.nexxus.cos.service.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {

    private final ProjectMapper projectMapper;

    @Override
    public ProjectEntity getByName(String name) {
        LambdaQueryWrapper<ProjectEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProjectEntity::getName, name);
        return projectMapper.selectOne(queryWrapper);
    }

    @Override
    public ProjectEntity getByDisplayId(String displayId) {
        LambdaQueryWrapper<ProjectEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProjectEntity::getDisplayId, displayId);
        return projectMapper.selectOne(queryWrapper);    }

    @Override
    public Page<ProjectEntity> listProjects(Long orgId, Long page, Long pageSize) {
        Page<ProjectEntity> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<ProjectEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (orgId != null && orgId != 0) {
            queryWrapper.eq(ProjectEntity::getOrgId, orgId);
        }
        queryWrapper.orderByDesc(ProjectEntity::getId);
        return projectMapper.selectPage(pageParam, queryWrapper);
    }
}
