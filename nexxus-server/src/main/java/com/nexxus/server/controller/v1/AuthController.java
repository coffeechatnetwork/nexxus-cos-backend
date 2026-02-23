package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.dto.LoginRequest;
import com.nexxus.auth.api.dto.RegisterRequest;
import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthApi authApi;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest req) {
        return authApi.register(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest req) {
        log.info("received login request, orgId: {}, email: {}", req.getOrgId(), req.getEmail());
        return authApi.login(req);
    }
}
