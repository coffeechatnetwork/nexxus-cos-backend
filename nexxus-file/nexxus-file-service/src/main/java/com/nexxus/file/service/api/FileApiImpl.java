package com.nexxus.file.service.api;

import com.nexxus.common.vo.Attachment;
import com.nexxus.file.service.config.S3Config;
import com.nexxus.file.service.service.S3Service;
import com.nexxxus.file.api.FileApi;
import com.nexxxus.file.api.dto.FileUploadRequest;
import com.nexxxus.file.api.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.net.URL;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileApiImpl implements FileApi {
    private final S3Service s3Service;
    private final S3Config s3Config;

    @Override
    public URL sign(URL orginalUrl) {
        return s3Service.sign(orginalUrl);
    }

    @Override
    public List<URL> batchSign(List<URL> orginalUrlList) {
        return List.of();
    }

    @Override
    public List<Attachment> signAttachments(List<Attachment> attachments) {
        return List.of();
    }

    @Override
    public FileUploadResponse upload(FileUploadRequest request) {
        log.info("Uploading file: key={}, contentType={}, size={}",
                request.getKey(), request.getContentType(), request.getContent().length);

        PutObjectResponse response = s3Service.upload(
                request.getKey(),
                request.getContent(),
                request.getContentType()
        );

        String fileUrl = buildFileUrl(request.getKey());

        return FileUploadResponse.builder()
                .key(request.getKey())
                .eTag(response.eTag().replace("\"", ""))
                .message("File uploaded successfully")
                .signedUrl(fileUrl)
                .build();
    }

    private String buildFileUrl(String key) {
        return String.format("%s/%s/%s", s3Config.getEndpoint(), s3Config.getBucket(), key);
    }
}
