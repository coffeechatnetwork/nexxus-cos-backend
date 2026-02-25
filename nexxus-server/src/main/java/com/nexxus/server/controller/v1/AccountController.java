package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.AccountApi;
import com.nexxus.auth.api.dto.AccountDto;
import com.nexxus.auth.api.dto.ListAccountRequest;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.PageResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountApi accountApi;

    @GetMapping("/me")
    public AccountDto getMyAccount() {
        AccountInfo accountInfo = AccountInfoContext.get();
        String displayId = accountInfo.getAccountId();
        return accountApi.getByDisplayId(displayId);
    }

    @GetMapping("/{displayId}")
    public AccountDto getAccountById(@PathVariable String displayId) {
        return accountApi.getByDisplayId(displayId);
    }

    @PostMapping("/list")
    public PageResult<AccountDto> listAccount(@RequestBody @Valid ListAccountRequest req) {
        return accountApi.listAccount(req.getPage(), req.getPageSize());
    }
}
