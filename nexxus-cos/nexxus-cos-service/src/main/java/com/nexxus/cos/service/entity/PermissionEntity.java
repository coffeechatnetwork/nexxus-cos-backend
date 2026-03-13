package com.nexxus.cos.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.HttpMethod;
import com.nexxus.common.enums.cos.permission.OperationCode;
import com.nexxus.common.enums.cos.permission.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("cos_permission")
public class PermissionEntity extends BaseEntity {
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
