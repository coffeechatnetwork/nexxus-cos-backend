package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.auth.CosAuthLoginRequest;
import com.nexxus.cos.api.dto.auth.CosAuthRegisterRequest;
import com.nexxus.cos.api.dto.auth.CosAuthResponse;
import com.nexxus.cos.api.dto.user.UserDto;

import java.util.UUID;

public interface UserApi {
    CosAuthResponse register(CosAuthRegisterRequest req);

    CosAuthResponse login(CosAuthLoginRequest req);

    UserDto getUserByAccountId(UUID accountId);

    UserDto getUserByAccountId(String accountIdStr);

    PageResult<UserDto> listUsers(Long page, Long pageSize);
}
