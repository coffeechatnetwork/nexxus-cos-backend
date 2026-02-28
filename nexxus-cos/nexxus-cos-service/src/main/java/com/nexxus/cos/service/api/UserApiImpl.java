package com.nexxus.cos.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthLoginRequest;
import com.nexxus.auth.api.dto.AuthRegisterRequest;
import com.nexxus.auth.api.dto.AuthResponse;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.common.enums.auth.AppCode;
import com.nexxus.common.enums.cos.user.UserStatus;
import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.auth.CosAuthLoginRequest;
import com.nexxus.cos.api.dto.auth.CosAuthRegisterRequest;
import com.nexxus.cos.api.dto.auth.CosAuthResponse;
import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        String avatarUrl = req.getAvatarUrl();
        UserEntity newUserEntity = UserEntity.builder()
                .accountId(accountID.toString())
                .orgId(req.getOrgId())
                .email(req.getEmail())
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
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

        return UserDto.builder()
                .accountId(userEntity.getAccountId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .avatarUrl(userEntity.getAvatarUrl())
                .status(userEntity.getStatus())
                .build();
    }

    @Override
    public PageResult<UserDto> listUsers(Long page, Long pageSize) {
        AccountInfo accountInfo = AccountInfoContext.get();
        Long orgId = accountInfo.getOrgId();
        Page<UserEntity> entityPage = userService.listUsers(orgId, page, pageSize);

        List<UserDto> userDtos = entityPage.getRecords().stream()
                .map(entity -> UserDto.builder()
                        .accountId(entity.getAccountId())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .username(entity.getUsername())
                        .email(entity.getEmail())
                        .avatarUrl(entity.getAvatarUrl())
                        .status(entity.getStatus())
                        .build()).collect(Collectors.toList());

        return PageResult.<UserDto>builder()
                .records(userDtos)
                .total(entityPage.getTotal())
                .pageSize(entityPage.getSize())
                .page(entityPage.getCurrent())
                .build();
    }
}
