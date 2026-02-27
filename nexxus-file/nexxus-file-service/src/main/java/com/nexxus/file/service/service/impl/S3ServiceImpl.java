package com.nexxus.file.service.service.impl;

import com.nexxus.file.service.config.S3Config;
import com.nexxus.file.service.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final S3Config s3Config;
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Override
    public URL sign(String url) {
        return sign(url, 24 * 60 * 60L);
    }

    @Override
    public URL sign(String url, Long signDuration) {
        String key = extractKeyFromUrl(url);
        String bucket = s3Config.getBucket();
        return presignGetObject(bucket, key, Duration.ofSeconds(signDuration));
    }

    @Override
    public URL presignGetObject(String bucket, String key, Duration expiration) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        GetObjectPresignRequest request = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(expiration)
                .build();

        return s3Presigner.presignGetObject(request).url();
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

    private String extractKeyFromUrl(String url) {
        String prefix = s3Config.getEndpoint() + "/" + s3Config.getBucket() + "/";
        if (url.startsWith(prefix)) {
            return url.substring(prefix.length());
        }
        throw new IllegalArgumentException("Invalid S3 URL format: " + url);
    }
}
