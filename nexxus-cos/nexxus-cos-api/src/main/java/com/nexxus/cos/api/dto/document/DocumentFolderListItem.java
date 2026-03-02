package com.nexxus.cos.api.dto.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentFolderListItem implements Serializable {
    private String name;
    private Long fileCount;
}
