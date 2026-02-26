package com.nexxxus.file.api.dto;

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
public class FileUploadRequest implements Serializable {
    @NotBlank
    private String key;

    @NotNull
    private byte[] content;

    @NotBlank
    private String contentType;
}
