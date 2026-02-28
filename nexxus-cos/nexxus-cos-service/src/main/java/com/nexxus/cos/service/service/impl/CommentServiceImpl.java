package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.CommentEntity;
import com.nexxus.cos.service.mapper.CommentMapper;
import com.nexxus.cos.service.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {
}
