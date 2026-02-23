package com.nexxus.auth.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    private final AccountMapper accountMapper;

    @Override
    public AccountEntity getByOrgIdAndEmail(Long orgId, String email) {
        LambdaQueryWrapper<AccountEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountEntity::getEmail, email);
        queryWrapper.eq(AccountEntity::getOrgId, orgId);
        return accountMapper.selectOne(queryWrapper);
    }

    @Override
    public AccountEntity getByDisplayId(String displayId) {
        LambdaQueryWrapper<AccountEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountEntity::getDisplayId, displayId);
        return accountMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<AccountEntity> listAccounts(Long page, Long pageSize) {
        Page<AccountEntity> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<AccountEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(AccountEntity::getId);
        return accountMapper.selectPage(pageParam, queryWrapper);
    }
}
