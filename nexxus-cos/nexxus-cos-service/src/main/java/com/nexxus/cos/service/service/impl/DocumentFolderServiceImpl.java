package com.nexxus.cos.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nexxus.cos.service.entity.DocumentFolderEntity;
import com.nexxus.cos.service.mapper.DocumentFolderMapper;
import com.nexxus.cos.service.service.DocumentFolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentFolderServiceImpl extends ServiceImpl<DocumentFolderMapper, DocumentFolderEntity> implements DocumentFolderService {
    @Override
    public DocumentFolderEntity getByProjectIdAndName(Long projectId, String name) {
        return lambdaQuery()
                .eq(DocumentFolderEntity::getProjectId, projectId)
                .eq(DocumentFolderEntity::getName, name)
                .one();
    }

    @Override
    public Page<DocumentFolderEntity> listFolders(Long projectId, Long page, Long pageSize) {
        return lambdaQuery()
                .eq(DocumentFolderEntity::getProjectId, projectId)
                .page(new Page<>(page, pageSize));
    }
}
