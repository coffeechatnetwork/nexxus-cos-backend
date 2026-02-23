package com.nexxus.auth.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexxus.auth.api.AccountApi;
import com.nexxus.auth.api.dto.AccountDto;
import com.nexxus.auth.service.entity.AccountEntity;
import com.nexxus.auth.service.service.AccountService;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountApiImpl implements AccountApi {
    private final AccountService accountService;

    @Override
    public AccountDto getByDisplayId(String displayId) {
        AccountEntity accountEntity = accountService.getByDisplayId(displayId);
        if (accountEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("account not found"));
        }

        return AccountDto.builder()
                .displayId(accountEntity.getDisplayId())
                .orgId(accountEntity.getOrgId())
                .type(accountEntity.getType())
                .countryCode(accountEntity.getCountryCode())
                .phoneNumber(accountEntity.getPhoneNumber())
                .email(accountEntity.getEmail())
                .externalId(accountEntity.getExternalId())
                .status(accountEntity.getStatus())
                .build();
    }

    @Override
    public PageResult<AccountDto> listAccount(Long page, Long pageSize) {
        Page<AccountEntity> entityPage = accountService.listAccounts(page, pageSize);

        List<AccountDto> dtoList = entityPage.getRecords().stream()
                .map(entity -> AccountDto.builder()
                        .displayId(entity.getDisplayId())
                        .orgId(entity.getOrgId())
                        .type(entity.getType())
                        .countryCode(entity.getCountryCode())
                        .phoneNumber(entity.getPhoneNumber())
                        .email(entity.getEmail())
                        .externalId(entity.getExternalId())
                        .status(entity.getStatus())
                        .build())
                .collect(Collectors.toList());

        return PageResult.<AccountDto>builder()
                .records(dtoList)
                .total(entityPage.getTotal())
                .pageSize(entityPage.getSize())
                .page(entityPage.getCurrent())
                .build();
    }
}
