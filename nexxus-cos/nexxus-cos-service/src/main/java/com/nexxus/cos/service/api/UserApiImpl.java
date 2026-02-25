package com.nexxus.cos.service.api;

import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthLoginRequest;
import com.nexxus.auth.api.dto.AuthRegisterRequest;
import com.nexxus.auth.api.dto.AuthResponse;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.auth.AppCode;
import com.nexxus.common.enums.cos.user.UserStatus;
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

import java.net.URL;
import java.util.Optional;
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
        UUID accountID = authResponse.getAccountId();
        String avatarUrl = Optional.ofNullable(req.getAvatarUrl())
                .map(URL::toString)
                .orElse(null);
        UserEntity newUserEntity = UserEntity.builder()
                .accountId(accountID.toString())
                .orgId(req.getOrgId())
                .email(req.getEmail())
                .username(req.getUsername())
                .avatarUrl(avatarUrl)
                .status(UserStatus.ACTIVE)
                .build();
        userService.save(newUserEntity);
        return CosAuthResponse.builder()
                .token(authResponse.getToken())
                .tokenType(authResponse.getTokenType())
                .expiresInSeconds(authResponse.getExpiresInSeconds())
                .accountId(authResponse.getAccountId())
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
                .accountId(authResponse.getAccountId())
                .build();
    }

    @Override
    public UserDto getUserByAccountId(String accountId) {
        UserEntity userEntity = userService.getByAccountId(accountId);
        if (userEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("user of this accountId not found"));
        }

        URL avatarUrl = Optional.ofNullable(userEntity.getAvatarUrl())
                .map(urlStr -> {
                    try {
                        return new URL(urlStr);
                    } catch (Exception e) {
                        log.warn("Invalid avatar URL for user: {}", accountId, e);
                        return null;
                    }
                })
                .orElse(null);

        return UserDto.builder()
                .accountId(userEntity.getAccountId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .avatarUrl(avatarUrl)
                .status(userEntity.getStatus())
                .build();
    }

    @Override
    public PageResult<UserDto> listUsers(Long page, Long pageSize) {
        return null;
    }
}
