package com.nexxus.auth.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.auth.service.entity.AccountEntity;
import com.nexxus.auth.service.mapper.AccountMapper;
import com.nexxus.auth.service.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements AccountService {
}
