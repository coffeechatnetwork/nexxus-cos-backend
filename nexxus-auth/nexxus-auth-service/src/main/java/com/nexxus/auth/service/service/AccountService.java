package com.nexxus.auth.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.auth.service.entity.AccountEntity;

public interface AccountService extends IService<AccountEntity> {

    public AccountEntity getByEmail(String email);
}
