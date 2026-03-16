package com.nexxus.server.controller.v1;

import com.nexxus.cos.api.PermissionApi;
import com.nexxus.cos.api.dto.permission.CreatePermissionRequest;
import com.nexxus.cos.api.dto.permission.PermissionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionApi permissionApi;

    @PostMapping("")
    public PermissionDto createPermission(@RequestBody @Valid CreatePermissionRequest req) {
        return permissionApi.createPermission(req);
    }
}
