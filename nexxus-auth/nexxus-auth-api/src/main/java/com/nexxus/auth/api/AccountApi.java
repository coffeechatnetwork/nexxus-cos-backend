package com.nexxus.auth.api;

import com.nexxus.auth.api.dto.AccountDto;
import com.nexxus.common.PageResult;

public interface AccountApi {
    AccountDto getByDisplayId(String displayId);

    PageResult<AccountDto> listAccount(Long page, Long pageSize);
}
