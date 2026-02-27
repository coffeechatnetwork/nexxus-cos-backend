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
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileApiImpl implements FileApi {
    private final S3Service s3Service;
    private final S3Config s3Config;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public URL sign(String originalUrl) {
        return s3Service.sign(originalUrl);
    }

    @Override
    public List<URL> batchSign(List<URL> orginalUrlList) {
        return orginalUrlList.stream()
                .map(url -> CompletableFuture.supplyAsync(() -> sign(url.toString()), executorService))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    @Override
    public List<Attachment> signAttachments(List<Attachment> attachments) {
        if (CollectionUtils.isEmpty(attachments)) {
            return List.of();
        }

        return attachments.stream()
                .map(attachment -> {
                    URL signedUrl = sign(attachment.getUrl());
                    return Attachment.builder()
                            .name(attachment.getName())
                            .url(signedUrl.toString())
                            .build();
                }).collect(Collectors.toList());
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
        URL signedUrl = this.sign(fileUrl);

        return FileUploadResponse.builder()
                .key(request.getKey())
                .eTag(response.eTag().replace("\"", ""))
                .message("File uploaded successfully")
                .signedUrl(signedUrl.toString())
                .build();
    }

    private String buildFileUrl(String key) {
        return String.format("%s/%s/%s", s3Config.getEndpoint(), s3Config.getBucket(), key);
    }
}
