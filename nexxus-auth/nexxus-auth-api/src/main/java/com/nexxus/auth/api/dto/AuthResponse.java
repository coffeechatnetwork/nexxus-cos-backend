package com.nexxus.auth.api.dto;

import com.nexxus.common.enums.Gender;
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
public class AuthResponse implements Serializable {
    private String token;

    @Builder.Default
    private String tokenType = "Bearer";

    private Long expiresInSeconds;
}
