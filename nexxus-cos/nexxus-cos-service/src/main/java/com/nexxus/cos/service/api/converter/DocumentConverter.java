package com.nexxus.cos.service.api.converter;

import com.nexxus.cos.api.dto.document.FileDto;
import com.nexxus.cos.api.dto.document.FolderDto;
import com.nexxus.cos.api.dto.document.FolderListItem;
import com.nexxus.cos.service.entity.DocumentFileEntity;
import com.nexxus.cos.service.entity.DocumentFolderEntity;
import com.nexxus.cos.service.service.DocumentFileService;
import com.nexxus.cos.service.service.DocumentFolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentConverter {

    private final DocumentFileService documentFileService;
    private final DocumentFolderService documentFolderService;

    public FolderDto toFolderDto(DocumentFolderEntity entity) {
        Long fileCount = documentFileService.countFilesByFolder(entity.getProjectId(), entity.getId());
        return FolderDto.builder()
                .projectId(entity.getProjectId())
                .name(entity.getName())
                .fileCount(fileCount)
                .build();
    }

    public FileDto toFileDto(DocumentFileEntity fileEntity) {
        DocumentFolderEntity folderEntity = documentFolderService.getById(fileEntity.getFolderId());
        return FileDto.builder()
                .projectId(fileEntity.getProjectId())
                .folderName(folderEntity.getName())
                .name(fileEntity.getName())
                .docUrl(fileEntity.getDocUrl())
                .build();
    }

    public FolderListItem toFolderListItem(DocumentFolderEntity entity) {
        Long fileCount = documentFileService.countFilesByFolder(entity.getProjectId(), entity.getId());
        return FolderListItem.builder()
                .projectId(entity.getProjectId())
                .name(entity.getName())
                .fileCount(fileCount)
                .build();
    }
}
