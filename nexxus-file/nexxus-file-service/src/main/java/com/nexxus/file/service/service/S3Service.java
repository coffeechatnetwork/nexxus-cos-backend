package com.nexxus.file.service.service;

import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.net.URL;

public interface S3Service {
    URL sign(URL url);

    PutObjectResponse upload(String key, byte[] content, String contentType);
}
