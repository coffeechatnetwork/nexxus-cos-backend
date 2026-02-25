package com.nexxus.auth.api.dto;

import com.nexxus.common.enums.auth.AppCode;
import com.nexxus.common.validations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {
    private Long orgId;
    @NotNull
    private AppCode appCode;
    @Email
    private String email;
    @ValidPassword
    private String password;
}
