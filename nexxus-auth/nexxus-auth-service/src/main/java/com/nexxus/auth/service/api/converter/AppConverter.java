package com.nexxus.auth.service.api.converter;

import com.nexxus.auth.api.dto.AppDto;
import com.nexxus.auth.service.entity.AppEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppConverter {

    public AppDto toAppDto(AppEntity entity) {
        return AppDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .build();
    }
}
