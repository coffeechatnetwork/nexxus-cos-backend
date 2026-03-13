package com.nexxus.cos.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
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
@TableName("cos_user_role")
public class UserRoleEntity extends BaseEntity {
    private Long accountId;
    private Long projectId;
    private Long roleId;
}
