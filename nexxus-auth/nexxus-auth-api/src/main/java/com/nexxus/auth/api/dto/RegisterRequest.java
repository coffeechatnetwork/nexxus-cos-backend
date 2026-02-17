package com.nexxus.auth.api.dto;

import com.nexxus.common.validations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {
    @Email
    private String email;
    @ValidPassword
    private String password;
    @NotBlank
    private String orgId;
}
