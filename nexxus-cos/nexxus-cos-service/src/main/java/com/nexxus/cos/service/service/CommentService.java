package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.common.enums.cos.comment.EntityType;
import com.nexxus.cos.service.entity.CommentEntity;

public interface CommentService extends IService<CommentEntity> {
    Page<CommentEntity> listComments(String entityId, EntityType entityType, Long page, Long pageSize);
}
