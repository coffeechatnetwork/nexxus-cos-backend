package com.nexxus.auth.service.service.impl;

import com.nexxus.auth.service.config.JwtProperties;
import com.nexxus.auth.service.service.JwtService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;
    private final RSASSASigner rsassaSigner;

    @Override
    public SignedJWT generateJWT(String subject, List<String> audiences, String orgId, String email) throws JOSEException {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(jwtProperties.getJwtTtl());
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder()
                .subject(subject)
                .audience(audiences)
                .issuer(jwtProperties.getIssuer())
                .issueTime(new Date())
                .expirationTime(Date.from(exp))
                .claim("orgId", orgId);
        if (email != null) {
            builder.claim("email", email);
        }
        JWTClaimsSet claimsSet = builder.build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
        signedJWT.sign(this.rsassaSigner);
        return signedJWT;
    }
}
