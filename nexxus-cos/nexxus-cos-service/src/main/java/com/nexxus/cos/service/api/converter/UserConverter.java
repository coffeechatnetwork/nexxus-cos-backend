package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.user.UserDto;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxxus.file.api.FileApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final FileApi fileApi;

    public UserDto toUserDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return UserDto.builder()
                .accountId(userEntity.getAccountId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .avatarUrl(fileApi.sign(userEntity.getAvatarUrl()))
                .status(userEntity.getStatus())
                .build();
    }
}
