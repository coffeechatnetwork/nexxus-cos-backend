package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.common.enums.cos.comment.EntityType;
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

    private final CommentMapper commentMapper;

    @Override
    public Page<CommentEntity> listComments(String entityId, EntityType entityType, Long page, Long pageSize) {
        Page<CommentEntity> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<CommentEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommentEntity::getEntityId, entityId);
        queryWrapper.eq(CommentEntity::getEntityType, entityType);
        queryWrapper.orderByDesc(CommentEntity::getId);
        return commentMapper.selectPage(pageParam, queryWrapper);
    }
}
