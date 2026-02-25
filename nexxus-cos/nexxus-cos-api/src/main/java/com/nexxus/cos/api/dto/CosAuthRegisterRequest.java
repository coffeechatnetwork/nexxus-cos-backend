package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.auth.AccountType;
import com.nexxus.common.enums.auth.AppCode;
import com.nexxus.common.validations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CosAuthRegisterRequest implements Serializable {
    @Email
    private String email;
    @ValidPassword
    private String password;
    private String username;
    @NotNull
    private Long orgId;
    @Builder.Default
    private AppCode appCode = AppCode.COS;
    @NotNull
    private AccountType type;
}
