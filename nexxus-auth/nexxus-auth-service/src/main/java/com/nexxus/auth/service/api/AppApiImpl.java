package com.nexxus.auth.service.api;

import com.nexxus.auth.api.AppApi;
import com.nexxus.auth.api.dto.AppDto;
import com.nexxus.auth.api.dto.CreateAppRequest;
import com.nexxus.auth.service.api.converter.AppConverter;
import com.nexxus.auth.service.entity.AppEntity;
import com.nexxus.auth.service.service.AppService;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.enums.auth.AppCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppApiImpl implements AppApi {
    private final AppService appService;
    private final AppConverter appConverter;

    @Override
    public AppDto createApp(CreateAppRequest req) {
        AppEntity existingApp = appService.getByCode(req.getCode());
        if (existingApp != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("app already exists"));
        }

        AppEntity appEntity = AppEntity.builder()
                .name(req.getName())
                .code(req.getCode())
                .build();

        appService.save(appEntity);

        return appConverter.toAppDto(appEntity);
    }

    @Override
    public AppDto getByCode(AppCode appCode) {
        AppEntity appEntity = appService.getByCode(appCode);
        if (appEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("app not found"));
        }
        return appConverter.toAppDto(appEntity);
    }
}
