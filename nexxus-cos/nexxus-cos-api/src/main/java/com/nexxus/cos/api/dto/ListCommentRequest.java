package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.cos.comment.EntityType;
import jakarta.validation.constraints.Max;
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
public class ListCommentRequest implements Serializable {
    @NotBlank
    private String entityId;
    @NotNull
    private EntityType entityType;
    @NotNull
    private Long page;
    @NotNull
    @Max(value = 50)
    private Long pageSize;
}
