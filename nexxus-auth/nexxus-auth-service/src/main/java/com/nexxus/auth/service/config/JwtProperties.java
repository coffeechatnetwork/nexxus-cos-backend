package com.nexxus.auth.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String issuer;
    private Long jwtTtl;
    private String publicKey;
    private String privateKey;
}
