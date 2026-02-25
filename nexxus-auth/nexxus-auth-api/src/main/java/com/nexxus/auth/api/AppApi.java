package com.nexxus.auth.api;

import com.nexxus.auth.api.dto.AppDto;
import com.nexxus.auth.api.dto.CreateAppRequest;
import com.nexxus.common.enums.auth.AppCode;

public interface AppApi {
    AppDto createApp(CreateAppRequest req);

    AppDto getByCode(AppCode appCode);
}
