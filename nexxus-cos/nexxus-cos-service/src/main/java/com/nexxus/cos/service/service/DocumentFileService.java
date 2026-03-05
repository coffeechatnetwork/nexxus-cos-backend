package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.DocumentFileEntity;

public interface DocumentFileService extends IService<DocumentFileEntity> {
    Long countFilesByFolder(Long projectId, Long folderId);

    DocumentFileEntity getFileInFolder(Long projectId, Long folderId, String name);

}
