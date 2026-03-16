package com.nexxus.cos.api.dto.permission;

import com.nexxus.common.enums.HttpMethod;
import com.nexxus.common.enums.cos.permission.OperationCode;
import com.nexxus.common.enums.cos.permission.PermissionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionRequest implements Serializable {
    @NotBlank
    @Size(max = 255)
    private String code;
    @NotNull
    private PermissionType type;
    @NotBlank
    @Size(max = 64)
    private String moduleName;
    @NotBlank
    @Size(max = 64)
    private String moduleCode;
    @NotBlank
    @Size(max = 64)
    private String featureName;
    @NotBlank
    @Size(max = 64)
    private String featureCode;
    @NotNull
    private OperationCode operationCode;
    @Size(max = 255)
    private String urlPattern;
    private HttpMethod httpMethod;
}

