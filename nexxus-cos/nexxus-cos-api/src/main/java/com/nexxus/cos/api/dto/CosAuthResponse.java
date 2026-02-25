package com.nexxus.cos.api.dto;

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
public class CosAuthResponse implements Serializable {
    private String token;

    @Builder.Default
    private String tokenType = "Bearer";

    private Long expiresInSeconds;
}
