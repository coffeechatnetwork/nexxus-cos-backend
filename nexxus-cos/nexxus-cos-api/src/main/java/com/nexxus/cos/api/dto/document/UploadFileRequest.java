package com.nexxus.cos.api.dto.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileRequest implements Serializable {
    @NotBlank
    private String folderName;

    @NotBlank
    private String fileName;

    @NotBlank
    private String key;

    @NotNull
    private byte[] content;

    @NotBlank
    private String contentType;
}
