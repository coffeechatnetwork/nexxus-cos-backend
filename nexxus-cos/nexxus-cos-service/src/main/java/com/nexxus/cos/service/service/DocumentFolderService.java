package com.nexxus.cos.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nexxus.cos.service.entity.DocumentFolderEntity;

public interface DocumentFolderService extends IService<DocumentFolderEntity> {
    DocumentFolderEntity getByProjectIdAndName(Long projectId, String name);

    Page<DocumentFolderEntity> listFolders(Long projectId, Long page, Long pageSize);
}
