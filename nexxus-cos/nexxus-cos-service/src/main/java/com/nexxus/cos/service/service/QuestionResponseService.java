package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.api.dto.question.ResponseDto;
import com.nexxus.cos.service.entity.QuestionResponseEntity;

public interface QuestionResponseService extends IService<QuestionResponseEntity> {
    ResponseDto getLastestResponse(Long questionId);
}
