package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
