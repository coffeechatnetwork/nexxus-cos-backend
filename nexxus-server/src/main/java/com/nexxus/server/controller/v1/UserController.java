package com.nexxus.server.controller.v1;

import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApi userApi;

    @GetMapping("/me")
    public UserDto me() {
        AccountInfo accountInfo = AccountInfoContext.get();
        String accountId = accountInfo.getAccountId();
        return userApi.getUserByAccountId(accountId);
    }
}
