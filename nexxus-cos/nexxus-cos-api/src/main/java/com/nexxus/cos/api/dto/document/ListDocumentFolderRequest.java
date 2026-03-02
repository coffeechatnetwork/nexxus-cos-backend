package com.nexxus.cos.api.dto.document;

import jakarta.validation.constraints.Max;
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
public class ListDocumentFolderRequest implements Serializable {
    @NotNull
    private Long page;
    @NotNull
    @Max(value = 200)
    private Long pageSize;
}
