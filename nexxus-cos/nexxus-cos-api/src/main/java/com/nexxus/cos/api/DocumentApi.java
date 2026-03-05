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
    FolderDto createFolder(CreateFolderRequest req);

    PageResult<FolderListItem> listFolders(ListFolderRequest req);

    UploadToFolderResponse uploadToFolder(UploadFileRequest req);

    FolderDto renameFolder(RenameFolderRequest req);

    Boolean deleteFolder(DeleteFolderRequest req);

    FileDto renameFile(RenameFileRequest req);

    Boolean deleteFile(DeleteFileRequest req);
}
