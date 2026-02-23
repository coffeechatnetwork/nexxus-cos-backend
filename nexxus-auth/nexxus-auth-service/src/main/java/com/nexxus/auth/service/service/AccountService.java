package com.nexxus.auth.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.auth.service.entity.AccountEntity;
import com.nexxus.auth.service.entity.OrganizationEntity;

public interface AccountService extends IService<AccountEntity> {

    AccountEntity getByOrgIdAndEmail(Long orgId, String email);

    AccountEntity getByDisplayId(String displayId);

    Page<AccountEntity> listAccounts(Long page, Long pageSize);
}
