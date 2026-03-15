package com.nexxus.server.controller.v1;

import com.nexxus.cos.api.RoleApi;
import com.nexxus.cos.api.dto.role.CreateRoleRequest;
import com.nexxus.cos.api.dto.role.RoleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleApi roleApi;

    @PostMapping("")
    public RoleDto createRole(@RequestBody @Valid CreateRoleRequest req) {
        return roleApi.createRole(req);
    }
}
