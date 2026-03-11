package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthLoginRequest;
import com.nexxus.auth.api.dto.AuthRegisterRequest;
import com.nexxus.auth.api.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthApi authApi;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid AuthRegisterRequest req) {
        return authApi.register(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthLoginRequest req) {
        log.info("received login request, orgId: {}, email: {}", req.getOrgId(), req.getEmail());
        return authApi.login(req);
    }
}
