package com.nexxus.file.service.service.impl;

import com.nexxus.file.service.config.S3Config;
import com.nexxus.file.service.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.net.URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Config s3Config;
    private final S3Client s3Client;

    @Override
    public URL sign(URL url) {
        return null;
    }

    @Override
    public PutObjectResponse upload(String key, byte[] content, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(key)
                .contentType(contentType)
                .build();

        PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content));

        log.info("Uploaded file to S3: bucket={}, key={}, etag={}",
                s3Config.getBucket(), key, response.eTag());

        return response;
    }
}
