package com.nexxus.cos.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.PageResult;
import com.nexxus.cos.api.DocumentApi;
import com.nexxus.cos.api.dto.document.CreateFolderRequest;
import com.nexxus.cos.api.dto.document.DeleteFileRequest;
import com.nexxus.cos.api.dto.document.DeleteFolderRequest;
import com.nexxus.cos.api.dto.document.FileDto;
import com.nexxus.cos.api.dto.document.FolderDto;
import com.nexxus.cos.api.dto.document.FolderListItem;
import com.nexxus.cos.api.dto.document.ListFolderRequest;
import com.nexxus.cos.api.dto.document.RenameFileRequest;
import com.nexxus.cos.api.dto.document.RenameFolderRequest;
import com.nexxus.cos.api.dto.document.UploadFileRequest;
import com.nexxus.cos.api.dto.document.UploadToFolderResponse;
import com.nexxus.cos.service.api.converter.DocumentConverter;
import com.nexxus.cos.service.entity.DocumentFileEntity;
import com.nexxus.cos.service.entity.DocumentFolderEntity;
import com.nexxus.cos.service.service.DocumentFileService;
import com.nexxus.cos.service.service.DocumentFolderService;
import com.nexxxus.file.api.FileApi;
import com.nexxxus.file.api.dto.FileUploadRequest;
import com.nexxxus.file.api.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentApiImpl implements DocumentApi {

    private final DocumentFolderService documentFolderService;
    private final DocumentConverter documentConverter;
    private final DocumentFileService documentFileService;
    private final FileApi fileApi;

    @Override
    public FolderDto createFolder(CreateFolderRequest req) {
        AccountInfo accountInfo = AccountInfoContext.get();
        DocumentFolderEntity folderEntity = documentFolderService.getByProjectIdAndName(
                req.getProjectId(), req.getName());
        if (folderEntity != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("folder already exist"));
        }
        DocumentFolderEntity newFolder = DocumentFolderEntity.builder()
                .orgId(accountInfo.getOrgId())
                .projectId(req.getProjectId())
                .name(req.getName())
                .build();
        documentFolderService.save(newFolder);
        return documentConverter.toFolderDto(newFolder);
    }

    @Override
    public PageResult<FolderListItem> listFolders(ListFolderRequest req) {
        Page<DocumentFolderEntity> folderEntityPage = documentFolderService.listFolders(req.getProjectId(), req.getPage(), req.getPageSize());

        List<FolderListItem> folderListItems = folderEntityPage.getRecords().stream()
                .map(documentConverter::toFolderListItem)
                .collect(Collectors.toList());

        return PageResult.<FolderListItem>builder()
                .records(folderListItems)
                .total(folderEntityPage.getTotal())
                .pageSize(folderEntityPage.getSize())
                .page(folderEntityPage.getCurrent())
                .build();
    }

    @Override
    public UploadToFolderResponse uploadToFolder(UploadFileRequest req) {

        AccountInfo accountInfo = AccountInfoContext.get();
        Long orgId = accountInfo.getOrgId();
        Long projectId = req.getProjectId();
        String folderName = req.getFolderName();

        DocumentFolderEntity folderEntity = documentFolderService.getByProjectIdAndName(projectId, folderName);
        if (folderEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("folder not found."));
        }

        FileUploadRequest request = FileUploadRequest.builder()
                .key(req.getKey())
                .content(req.getContent())
                .contentType(req.getContentType())
                .build();
        FileUploadResponse uploadResponse = fileApi.upload(request);
        String docUrl = uploadResponse.getSignedUrl();
        DocumentFileEntity newDocumentFileEntity = DocumentFileEntity.builder()
                .orgId(orgId)
                .projectId(projectId)
                .folderId(folderEntity.getId())
                .name(req.getFileName())
                .docUrl(docUrl)
                .build();
        documentFileService.save(newDocumentFileEntity);
        return UploadToFolderResponse.builder()
                .signedUrl(docUrl)
                .build();
    }

    @Override
    public FolderDto renameFolder(RenameFolderRequest req) {
        Long projectId = req.getProjectId();
        Long folderId = req.getFolderId();
        String newName = req.getNewName();

        DocumentFolderEntity folderEntity = documentFolderService.getById(folderId);
        if (folderEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("folder not found"));
        }
        String originalName = folderEntity.getName();
        if (originalName.equals(newName)) {
            throw new IllegalArgumentException("new name is the same as the original name");
        }

        DocumentFolderEntity folderEntity1 = documentFolderService.getByProjectIdAndName(projectId, newName);

        if (folderEntity1 != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("folder with new name already exists"));
        }

        folderEntity.setName(req.getNewName());
        documentFolderService.updateById(folderEntity);

        return documentConverter.toFolderDto(folderEntity);
    }

    @Override
    public Boolean deleteFolder(DeleteFolderRequest req) {
        DocumentFolderEntity folderEntity = documentFolderService.getById(req.getFolderId());
        if (folderEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("folder not found"));
        }
        folderEntity.setDeletedAt(Instant.now());
        return documentFolderService.removeById(folderEntity);
    }

    @Override
    public FileDto renameFile(RenameFileRequest req) {
        DocumentFileEntity fileEntity = documentFileService.getById(req.getFileId());
        if (fileEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("file not found"));
        }
        String originalName = fileEntity.getName();
        if (originalName.equals(req.getNewName())) {
            throw new IllegalArgumentException("new name is the same as the original name");
        }
        DocumentFileEntity fileEntity1 = documentFileService.getFileInFolder(req.getProjectId(), req.getFolderId(), req.getNewName());
        if (fileEntity1 != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("file with new name already exists"));
        }
        fileEntity.setName(req.getNewName());
        documentFileService.updateById(fileEntity);
        return documentConverter.toFileDto(fileEntity);
    }

    @Override
    public Boolean deleteFile(DeleteFileRequest req) {
        DocumentFileEntity fileEntity = documentFileService.getById(req.getFileId());
        if (fileEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("file not found"));
        }
        fileEntity.setDeletedAt(Instant.now());
        return documentFileService.removeById(fileEntity);
    }

}
