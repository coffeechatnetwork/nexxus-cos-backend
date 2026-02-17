package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.dto.RegisterRequest;
import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthApi authApi;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest req) {
        authApi.register(req);
        return null;
    }
}
