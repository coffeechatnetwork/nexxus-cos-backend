package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.QuestionResponseEntity;

import java.util.List;

public interface QuestionResponseService extends IService<QuestionResponseEntity> {
    QuestionResponseEntity getLastestResponse(Long questionId);

    List<QuestionResponseEntity> getResponsesByQuestionId(Long questionId);
}
