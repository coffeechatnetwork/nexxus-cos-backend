package com.nexxus.auth.service.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
public class JwtConfig {

    private final JwtProperties jwtProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RSAKey rsaKey() throws JOSEException {
        final String publicKey = jwtProperties.getPublicKey();
        final String privateKey = jwtProperties.getPrivateKey();
        return (RSAKey) JWK.parseFromPEMEncodedObjects(publicKey + "\r\n" + privateKey);
    }

    @Bean
    public RSASSASigner rsassaSigner() throws JOSEException {
        RSAKey rsaKey = this.rsaKey();
        return new RSASSASigner(rsaKey.toPrivateKey());
    }
}
