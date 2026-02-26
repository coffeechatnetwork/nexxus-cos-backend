package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.auth.AccountType;
import com.nexxus.common.validations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

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
    @NotNull
    private AccountType type;
    @URL
    @Size(min = 1, max = 2048)
    private String avatarUrl;
}
