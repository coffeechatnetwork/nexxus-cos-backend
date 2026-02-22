package com.nexxus.auth.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.Gender;
import com.nexxus.common.enums.auth.AccountStatus;
import com.nexxus.common.enums.auth.AccountType;
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
@TableName("account")
public class AccountEntity extends BaseEntity {
    private String displayId;
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
