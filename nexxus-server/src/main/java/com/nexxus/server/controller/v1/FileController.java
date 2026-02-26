package com.nexxus.server.controller.v1;

import com.nexxxus.file.api.FileApi;
import com.nexxxus.file.api.dto.FileSignRequest;
import com.nexxxus.file.api.dto.FileSignResponse;
import com.nexxxus.file.api.dto.FileUploadRequest;
import com.nexxxus.file.api.dto.FileUploadResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileApi fileApi;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public FileUploadResponse upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "key", required = false) String key) throws IOException {

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        if (key == null || key.isEmpty()) {
            key = "uploads/" + fileName;
        }

        log.info("Received upload request: key={}, fileName={}, contentType={}, size={}",
                key, fileName, contentType, file.getSize());

        FileUploadRequest request = FileUploadRequest.builder()
                .key(key)
                .content(file.getBytes())
                .contentType(contentType)
                .build();

        return fileApi.upload(request);
    }

    @PostMapping(value = "/upload", consumes = "application/json")
    public FileUploadResponse uploadJson(@RequestBody @Valid FileUploadRequest request) {
        log.info("Received upload request: key={}, contentType={}",
                request.getKey(), request.getContentType());
        return fileApi.upload(request);
    }

    @PostMapping("/sign")
    public FileSignResponse signUrl(@RequestBody @Valid FileSignRequest req) {
        URL signedUrl = fileApi.sign(req.getOrginalUrl());
        return FileSignResponse.builder().signedUrl(signedUrl).build();
    }
}
