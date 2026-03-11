package com.nexxus.server.controller.v1;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.PageResult;
import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.user.ListUserRequest;
import com.nexxus.cos.api.dto.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApi userApi;

    @GetMapping("/me")
    public UserDto me() {
        AccountInfo accountInfo = AccountInfoContext.get();
        UUID accountId = accountInfo.getAccountId();
        return userApi.getUserByAccountId(accountId);
    }

    @GetMapping("/{accountId}")
    public UserDto getByAccountId(@PathVariable UUID accountId) {
        return userApi.getUserByAccountId(accountId);
    }

    @PostMapping("/list")
    public PageResult<UserDto> listUsers(@RequestBody @Valid ListUserRequest req) {
        return userApi.listUsers(req.getPage(), req.getPageSize());
    }
}
