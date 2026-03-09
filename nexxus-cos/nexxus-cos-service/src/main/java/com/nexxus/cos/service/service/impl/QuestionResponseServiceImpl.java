package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.common.enums.cos.question.ResponseStatus;
import com.nexxus.cos.api.dto.question.ResponseDto;
import com.nexxus.cos.service.api.converter.QuestionResponseConverter;
import com.nexxus.cos.service.entity.QuestionResponseEntity;
import com.nexxus.cos.service.mapper.QuestionResponseMapper;
import com.nexxus.cos.service.service.QuestionResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionResponseServiceImpl extends ServiceImpl<QuestionResponseMapper, QuestionResponseEntity> implements QuestionResponseService {

    private final QuestionResponseConverter questionResponseConverter;

    @Override
    public ResponseDto getLastestResponse(Long questionId) {
        LambdaQueryWrapper<QuestionResponseEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(QuestionResponseEntity::getQuestionId, questionId);
        queryWrapper.eq(QuestionResponseEntity::getStatus, ResponseStatus.PUBLISHED);
        queryWrapper.orderByDesc(QuestionResponseEntity::getUpdatedAt);
        queryWrapper.last("LIMIT 1");

        QuestionResponseEntity responseEntity = getOne(queryWrapper);
        if (responseEntity == null) {
            return null;
        }
        return questionResponseConverter.toResponseDto(responseEntity);
    }
}
