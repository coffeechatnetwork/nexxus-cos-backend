package com.nexxus.server.controller.v1;

import com.nexxus.auth.api.AppApi;
import com.nexxus.auth.api.dto.AppDto;
import com.nexxus.auth.api.dto.CreateAppRequest;
import com.nexxus.common.enums.auth.AppCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/apps")
@RequiredArgsConstructor
public class AppController {

    private final AppApi appApi;

    @PostMapping("")
    public AppDto createApp(@RequestBody @Valid CreateAppRequest req) {
        return appApi.createApp(req);
    }

    @GetMapping("/{appCode}")
    public AppDto getAppByCode(@PathVariable AppCode appCode) {
        return appApi.getByCode(appCode);
    }
}
