package com.nexxus.cos.api.dto.permission;

import com.nexxus.common.enums.HttpMethod;
import com.nexxus.common.enums.cos.permission.OperationCode;
import com.nexxus.common.enums.cos.permission.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto implements Serializable {
    private Long id;
    private String code;
    private PermissionType type;
    private String moduleName;
    private String moduleCode;
    private String featureName;
    private String featureCode;
    private OperationCode operationCode;
    private String urlPattern;
    private HttpMethod httpMethod;
}
