package com.nexxus.auth.api.dto;

import com.nexxus.common.validations.ValidPassword;
import jakarta.validation.constraints.Email;
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
    @Email
    private String email;
    @ValidPassword
    private String password;
}
