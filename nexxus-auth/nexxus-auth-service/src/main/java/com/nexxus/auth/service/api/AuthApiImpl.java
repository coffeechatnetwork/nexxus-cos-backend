package com.nexxus.auth.service.api;

import com.nexxus.auth.api.dto.RegisterRequest;
import com.nexxus.common.NexxusException;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthResponse;
import com.nexxus.auth.service.entity.AccountEntity;
import com.nexxus.auth.service.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthApiImpl implements AuthApi {

    private final AccountService accountService;

    @Override
    public AuthResponse register(RegisterRequest req) {
        String email = req.getEmail();
        AccountEntity existingAccount = accountService.getByEmail(email);
        if (existingAccount != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("account already exist"));
        }
        return null;
    }
}
