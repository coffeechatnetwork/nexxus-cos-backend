package com.nexxus.server.controller.v1;

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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects/{projectId}/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentApi documentApi;

    @PostMapping("/folders")
    public FolderDto createFolder(@PathVariable Long projectId, @RequestBody @Valid CreateFolderRequest req) {
        log.info("create folder req: {}", req);
        return documentApi.createFolder(projectId, req);
    }

    @PostMapping("/folders/list")
    public PageResult<FolderListItem> listFolders(@PathVariable Long projectId, @RequestBody @Valid ListFolderRequest req) {
        return documentApi.listFolders(projectId, req);
    }

    @PostMapping(value = "/folders/upload", consumes = "multipart/form-data")
    public UploadToFolderResponse uploadToFolder(@PathVariable Long projectId,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("folderName") String folderName) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file is not provided.");
        }
        String fileName = file.getOriginalFilename();
        String key = String.format("%d/%s/%s", projectId, folderName, fileName);
        String contentType = file.getContentType();
        UploadFileRequest fileRequest = UploadFileRequest.builder()
                .key(key)
                .content(file.getBytes())
                .folderName(folderName)
                .fileName(fileName)
                .contentType(contentType).build();
        return documentApi.uploadToFolder(projectId, fileRequest);
    }

    @PostMapping("/folders/rename")
    public FolderDto renameFolder(@PathVariable Long projectId, @RequestBody @Valid RenameFolderRequest req) {
        log.info("rename folder req: {}", req);
        return documentApi.renameFolder(projectId, req);
    }

    @PostMapping("/folders/delete")
    public Boolean deleteFolder(@PathVariable Long projectId, @RequestBody @Valid DeleteFolderRequest req) {
        log.info("delete folder req: {}", req);
        return documentApi.deleteFolder(projectId, req);
    }

    @PostMapping("/files/rename")
    public FileDto renameFile(@PathVariable Long projectId, @RequestBody @Valid RenameFileRequest req) {
        log.info("rename file req: {}", req);
        return documentApi.renameFile(projectId, req);
    }

    @PostMapping("/files/delete")
    public Boolean deleteFile(@PathVariable Long projectId, @RequestBody @Valid DeleteFileRequest req) {
        log.info("delete file req: {}", req);
        return documentApi.deleteFile(projectId, req);
    }
}
