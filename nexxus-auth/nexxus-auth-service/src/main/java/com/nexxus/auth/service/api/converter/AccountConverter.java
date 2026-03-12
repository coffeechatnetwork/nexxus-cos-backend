package com.nexxus.auth.service.api.converter;

import com.nexxus.auth.api.dto.AccountDto;
import com.nexxus.auth.service.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountConverter {
    public AccountDto toAccountDto(AccountEntity entity) {
        return AccountDto.builder()
                .displayId(entity.getDisplayId())
                .appCode(entity.getAppCode())
                .orgId(entity.getOrgId())
                .type(entity.getType())
                .countryCode(entity.getCountryCode())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .externalId(entity.getExternalId())
                .status(entity.getStatus())
                .build();
    }
}
