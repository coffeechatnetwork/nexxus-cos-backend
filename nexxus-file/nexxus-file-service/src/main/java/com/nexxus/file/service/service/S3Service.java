package com.nexxus.file.service.service;

import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.net.URL;
import java.time.Duration;

public interface S3Service {
    URL sign(String url);

    URL sign(String url, Long signDuration);

    URL presignGetObject(String bucket, String key, Duration expiration);

    PutObjectResponse upload(String key, byte[] content, String contentType);
}
