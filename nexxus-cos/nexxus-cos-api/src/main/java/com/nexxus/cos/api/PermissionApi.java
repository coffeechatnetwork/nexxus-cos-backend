package com.nexxus.cos.api;

import com.nexxus.cos.api.dto.permission.CreatePermissionRequest;
import com.nexxus.cos.api.dto.permission.PermissionDto;

public interface PermissionApi {
    PermissionDto createPermission(CreatePermissionRequest req);
}
