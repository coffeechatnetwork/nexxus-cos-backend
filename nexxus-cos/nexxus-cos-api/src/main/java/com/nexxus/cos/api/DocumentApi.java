package com.nexxus.cos.api;

import com.nexxus.common.PageResult;
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

public interface DocumentApi {
    FolderDto createFolder(Long projectId, CreateFolderRequest req);

    PageResult<FolderListItem> listFolders(Long projectId, ListFolderRequest req);

    UploadToFolderResponse uploadToFolder(Long projectId, UploadFileRequest req);

    FolderDto renameFolder(Long projectId, RenameFolderRequest req);

    Boolean deleteFolder(Long projectId, DeleteFolderRequest req);

    FileDto renameFile(Long projectId, RenameFileRequest req);

    Boolean deleteFile(Long projectId, DeleteFileRequest req);
}
