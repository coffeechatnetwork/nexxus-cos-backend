package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
import com.nexxus.cos.api.dto.CosAuthLoginRequest;
import com.nexxus.cos.api.dto.CosAuthRegisterRequest;
import com.nexxus.cos.api.dto.CosAuthResponse;
import com.nexxus.cos.api.dto.UserDto;

public interface UserApi {
    CosAuthResponse register(CosAuthRegisterRequest req);

    CosAuthResponse login(CosAuthLoginRequest req);

    UserDto getUserByAccountId(String accountId);

    PageResult<UserDto> listUsers(Long page, Long pageSize);
}
