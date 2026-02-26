package com.nexxxus.file.api;

import com.nexxus.common.vo.Attachment;
import com.nexxxus.file.api.dto.FileUploadRequest;
import com.nexxxus.file.api.dto.FileUploadResponse;

import java.net.URL;
import java.util.List;

public interface FileApi {

    URL sign(URL orginalUrl);

    List<URL> batchSign(List<URL> orginalUrlList);

    List<Attachment> signAttachments(List<Attachment> attachments);

    FileUploadResponse upload(FileUploadRequest request);
}
