package com.nexxxus.file.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse implements Serializable {
    private String key;
    private String eTag;
    private String message;
    private String signedUrl;
}
