package com.nexxus.auth.service.api;

import com.nexxus.common.NexxusException;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.auth.api.UserApi;
import com.nexxus.auth.api.dto.UserDTO;
import com.nexxus.auth.service.entity.UserEntity;
import com.nexxus.auth.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    @Override
    public UserDTO getUserById(Long userId) {
        UserEntity userEntity = userService.getById(userId);
        if (userEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("User Not Found"));
        }
        return UserDTO.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .gender(userEntity.getGender())
                .build();
    }
}
