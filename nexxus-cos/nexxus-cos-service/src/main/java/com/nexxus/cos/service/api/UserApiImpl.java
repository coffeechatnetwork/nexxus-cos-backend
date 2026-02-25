package com.nexxus.cos.service.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.CosAuthLoginRequest;
import com.nexxus.cos.api.dto.CosAuthRegisterRequest;
import com.nexxus.cos.api.dto.CosAuthResponse;
import com.nexxus.cos.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {
    @Override
    public CosAuthResponse register(CosAuthRegisterRequest req) {
        return null;
    }

    @Override
    public CosAuthResponse login(CosAuthLoginRequest req) {
        return null;
    }

    @Override
    public UserDto getUserByDisplayId(String displayId) {
        return null;
    }

    @Override
    public PageResult<UserDto> listUsers(Long page, Long pageSize) {
        return null;
    }
}
