package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.AppApi;
import com.nexxus.auth.api.dto.AppDto;
import com.nexxus.auth.api.dto.CreateAppRequest;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.enums.auth.AppCode;
import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.auth.CosAuthLoginRequest;
import com.nexxus.cos.api.dto.auth.CosAuthRegisterRequest;
import com.nexxus.cos.api.dto.auth.CosAuthResponse;
import com.nexxus.server.controller.v1.dto.AuthResp;
import com.nexxus.server.controller.v1.dto.LoginRequest;
import com.nexxus.server.controller.v1.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/apps")
@RequiredArgsConstructor
public class AppController {

    private final AppApi appApi;
    private final UserApi userApi;

    @PostMapping("")
    public AppDto createApp(@RequestBody @Valid CreateAppRequest req) {
        return appApi.createApp(req);
    }

    @GetMapping("/{appCode}")
    public AppDto getAppByCode(@PathVariable AppCode appCode) {
        return appApi.getByCode(appCode);
    }

    @PostMapping("/{appCode}/register")
    public AuthResp register(@PathVariable AppCode appCode,
                             @RequestBody @Valid RegisterRequest req) {
        AuthResp resp;
        switch (appCode) {
            case COS:
                CosAuthRegisterRequest cosRegisterReq = CosAuthRegisterRequest.builder()
                        .email(req.getEmail())
                        .password(req.getPassword())
                        .firstName(req.getFirstName())
                        .lastName(req.getLastName())
                        .username(req.getUsername())
                        .orgId(req.getOrgId())
                        .type(req.getType())
                        .avatarUrl(req.getAvatarUrl())
                        .build();
                CosAuthResponse cosAuthResp = userApi.register(cosRegisterReq);
                resp = AuthResp.builder()
                        .token(cosAuthResp.getToken())
                        .tokenType(cosAuthResp.getTokenType())
                        .expiresInSeconds(cosAuthResp.getExpiresInSeconds())
                        .accountId(cosAuthResp.getAccountId())
                        .build();
                break;
            default:
                throw new NexxusException(ErrorDefEnum.NOT_IMPLEMENTED.desc("this app is not supported"));
        }
        return resp;
    }

    @PostMapping("/{appCode}/login")
    public AuthResp login(@PathVariable AppCode appCode,
                          @RequestBody @Valid LoginRequest req) {
        AuthResp resp;
        switch (appCode) {
            case COS:
                CosAuthLoginRequest cosLoginReq = CosAuthLoginRequest.builder()
                        .email(req.getEmail()).password(req.getPassword()).build();
                CosAuthResponse cosAuthResp = userApi.login(cosLoginReq);
                resp = AuthResp.builder()
                        .token(cosAuthResp.getToken())
                        .tokenType(cosAuthResp.getTokenType())
                        .expiresInSeconds(cosAuthResp.getExpiresInSeconds())
                        .accountId(cosAuthResp.getAccountId())
                        .build();
                break;
            default:
                throw new NexxusException(ErrorDefEnum.NOT_IMPLEMENTED.desc("this app is not supported"));
        }
        return resp;
    }
}
