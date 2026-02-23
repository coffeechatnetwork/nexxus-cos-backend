package com.nexxus.auth.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.auth.service.entity.OrganizationEntity;
import com.nexxus.auth.service.mapper.OrganizationMapper;
import com.nexxus.auth.service.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, OrganizationEntity> implements OrganizationService {

    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationEntity getByDisplayId(String displayId) {
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationEntity::getDisplayId, displayId);
        return organizationMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<OrganizationEntity> listOrganizations(Long page, Long pageSize) {
        Page<OrganizationEntity> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<OrganizationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(OrganizationEntity::getId);
        return organizationMapper.selectPage(pageParam, queryWrapper);
    }
}
