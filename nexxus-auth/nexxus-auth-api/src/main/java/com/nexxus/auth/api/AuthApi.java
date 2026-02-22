package com.nexxus.auth.api;

import com.nexxus.auth.api.dto.AuthResponse;
import com.nexxus.auth.api.dto.LoginRequest;
import com.nexxus.auth.api.dto.RegisterRequest;

public interface AuthApi {
    AuthResponse register(RegisterRequest req);

    AuthResponse login(LoginRequest req);
}
