package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.question.ResponseDto;
import com.nexxus.cos.service.entity.QuestionResponseEntity;
import com.nexxus.cos.service.entity.UserEntity;
import com.nexxus.cos.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionResponseConverter {

    private final UserService userService;
    private final UserConverter userConverter;

    public ResponseDto toResponseDto(QuestionResponseEntity entity) {
        UserEntity creator = userService.getByAccountId(entity.getCreatedBy());
        return ResponseDto.builder()
                .content(entity.getContent())
                .status(entity.getStatus())
                .createdBy(userConverter.toUserDto(creator))
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
