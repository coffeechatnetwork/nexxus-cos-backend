package com.nexxus.auth.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.auth.AccountStatus;
import com.nexxus.common.enums.auth.AccountType;
import com.nexxus.common.enums.auth.AppCode;
import com.nexxus.common.handlers.UuidTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "account", autoResultMap = true)
public class AccountEntity extends BaseEntity {
    @TableField(typeHandler = UuidTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private UUID displayId;
    private AppCode appCode;
    private Long orgId;
    private AccountType type;
    private String countryCode;
    private String phoneNumber;
    private String email;
    private String password;
    private String salt;
    private String externalId;
    private AccountStatus status;

}
