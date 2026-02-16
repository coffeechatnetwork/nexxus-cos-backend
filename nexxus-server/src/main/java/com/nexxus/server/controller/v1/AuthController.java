package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.dto.RegisterRequest;
import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthApi authApi;

    @GetMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest req) {
        return null;
    }
}
