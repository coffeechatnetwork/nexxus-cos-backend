package com.nexxus.auth.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.auth.service.entity.OrganizationEntity;

public interface OrganizationService extends IService<OrganizationEntity> {
    public OrganizationEntity getByDisplayId(String displayId);
}
