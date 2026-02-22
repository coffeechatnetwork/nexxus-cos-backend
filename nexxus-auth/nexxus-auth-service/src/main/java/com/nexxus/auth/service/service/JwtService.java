package com.nexxus.auth.service.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.util.List;

public interface JwtService {

    SignedJWT generateJWT(String subject, List<String> audiences, String orgId, String email) throws JOSEException;
}
