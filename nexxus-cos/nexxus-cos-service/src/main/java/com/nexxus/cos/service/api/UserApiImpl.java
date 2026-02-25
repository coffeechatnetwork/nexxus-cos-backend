package com.nexxus.cos.service.api;

import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthLoginRequest;
import com.nexxus.auth.api.dto.AuthRegisterRequest;
import com.nexxus.auth.api.dto.AuthResponse;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.auth.AppCode;
import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.CosAuthLoginRequest;
import com.nexxus.cos.api.dto.CosAuthRegisterRequest;
import com.nexxus.cos.api.dto.CosAuthResponse;
import com.nexxus.cos.api.dto.UserDto;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final AuthApi authApi;
    private final UserService userService;

    @Override
    public CosAuthResponse register(CosAuthRegisterRequest req) {
        String displayId = UUID.randomUUID().toString();
        AuthRegisterRequest authRegisterReq = AuthRegisterRequest.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .displayId(displayId)
                .orgId(req.getOrgId())
                .appCode(AppCode.COS).type(req.getType()).build();
        AuthResponse authResponse = authApi.register(authRegisterReq);
        return CosAuthResponse.builder()
                .token(authResponse.getToken())
                .tokenType(authResponse.getTokenType())
                .expiresInSeconds(authResponse.getExpiresInSeconds())
                .build();
    }

    @Override
    public CosAuthResponse login(CosAuthLoginRequest req) {
        String email = req.getEmail();
        UserEntity userEntity = userService.getByEmail(email);
        if (userEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("user of this email not found"));
        }
        AuthLoginRequest authLoginReq = AuthLoginRequest.builder()
                .orgId(userEntity.getOrgId())
                .appCode(AppCode.COS)
                .email(email)
                .password(req.getPassword())
                .build();
        AuthResponse authResponse = authApi.login(authLoginReq);
        return CosAuthResponse.builder()
                .token(authResponse.getToken())
                .tokenType(authResponse.getTokenType())
                .expiresInSeconds(authResponse.getExpiresInSeconds())
                .build();
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
