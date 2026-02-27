package com.nexxxus.file.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FileSignRequest implements Serializable {
    @URL
    @Size(min = 1, max = 2048)
    private String originalUrl;

    @Builder.Default
    private Long signDuration = 24 * 60 * 60L;
}
