package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.UserApi;
import com.nexxus.cos.api.dto.question.QuestionDto;
import com.nexxus.cos.service.entity.QuestionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionConverter {

    private final UserApi userApi;

    public QuestionDto toQuestionDto(QuestionEntity entity) {
        return null;
    }
}
