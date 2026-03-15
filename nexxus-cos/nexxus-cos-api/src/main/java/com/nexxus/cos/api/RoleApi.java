package com.nexxus.cos.api;

import com.nexxus.cos.api.dto.role.CreateRoleRequest;
import com.nexxus.cos.api.dto.role.RoleDto;

public interface RoleApi {
    RoleDto createRole(CreateRoleRequest req);
}
