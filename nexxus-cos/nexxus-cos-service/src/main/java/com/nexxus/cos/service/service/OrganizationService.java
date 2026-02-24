package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.OrganizationEntity;

public interface OrganizationService extends IService<OrganizationEntity> {

    OrganizationEntity getByDisplayId(String displayId);

    Page<OrganizationEntity> listOrganizations(Long page, Long pageSize);

    OrganizationEntity getByCode(String code);
}
