package com.nexxus.file.service.api;

import com.nexxus.common.vo.Attachment;
import com.nexxus.file.service.config.S3Config;
import com.nexxus.file.service.service.S3Service;
import com.nexxxus.file.api.FileApi;
import com.nexxxus.file.api.dto.FileSignRequest;
import com.nexxxus.file.api.dto.FileSignResponse;
import com.nexxxus.file.api.dto.FileUploadRequest;
import com.nexxxus.file.api.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
    public FileSignResponse sign(FileSignRequest req) {
        String originalUrl = req.getOriginalUrl();
        Long signDuration = req.getSignDuration();
        String signedUrl = sign(originalUrl, signDuration);
        return FileSignResponse.builder()
                .signedUrl(signedUrl)
                .build();
    }

    @Override
    public String sign(String originalUrl) {
        return sign(originalUrl, 24 * 60 * 60L);
    }

    public String sign(String originalUrl, Long signDuration) {
        if (!StringUtils.hasText(originalUrl)) {
            return null;
        }
        URL signedUrl = s3Service.sign(originalUrl, signDuration);
        return signedUrl.toString();
    }

    @Override
    public List<String> batchSign(List<String> originalUrls) {
        return originalUrls.stream()
                .map(url -> CompletableFuture.supplyAsync(() -> sign(url), executorService))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    @Override
    public List<URL> batchSignURL(List<URL> originalUrlList) {
        return originalUrlList.stream()
                .map(url -> CompletableFuture.supplyAsync(() -> s3Service.sign(url.toString()), executorService))
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
                    String signedUrl = sign(attachment.getUrl());
                    return Attachment.builder()
                            .name(attachment.getName())
                            .url(signedUrl)
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
        URL signedUrl = s3Service.sign(fileUrl);

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
