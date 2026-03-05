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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentApi documentApi;

    @PostMapping("/folders")
    public FolderDto createFolder(@RequestBody @Valid CreateFolderRequest req) {
        log.info("create folder req: {}", req);
        return documentApi.createFolder(req);
    }

    @PostMapping("/folders/list")
    public PageResult<FolderListItem> listFolders(@RequestBody @Valid ListFolderRequest req) {
        return documentApi.listFolders(req);
    }

    @PostMapping(value = "/folders/upload", consumes = "multipart/form-data")
    public UploadToFolderResponse uploadToFolder(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("projectId") Long projectId,
                                                 @RequestParam("folderName") String folderName) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file is not provided.");
        }
        String fileName = file.getOriginalFilename();
        String key = String.format("%d/%s/%s", projectId, folderName, fileName);
        String contentType = file.getContentType();
        UploadFileRequest fileRequest = UploadFileRequest.builder()
                .key(key)
                .projectId(projectId)
                .content(file.getBytes())
                .folderName(folderName)
                .fileName(fileName)
                .contentType(contentType).build();
        return documentApi.uploadToFolder(fileRequest);
    }

    @PostMapping("/folders/rename")
    public FolderDto renameFolder(@RequestBody @Valid RenameFolderRequest req) {
        return documentApi.renameFolder(req);
    }

    @PostMapping("/folders/delete")
    public Boolean deleteFolder(@RequestBody @Valid DeleteFolderRequest req) {
        return documentApi.deleteFolder(req);
    }

    @PostMapping("/files/rename")
    public FileDto renameFile(@RequestBody @Valid RenameFileRequest req) {
        return documentApi.renameFile(req);
    }

    @PostMapping("/files/delete")
    public Boolean deleteFile(@RequestBody @Valid DeleteFileRequest req) {
        return documentApi.deleteFile(req);
    }
}
