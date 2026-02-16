package com.nexxus.auth.api;

import com.nexxus.auth.api.dto.UserDTO;

public interface UserApi {
    UserDTO getUserById(Long userId);
}
