package com.nexxus.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexxus.auth.api.AccountApi;
import com.nexxus.auth.api.AuthApi;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import com.nexxus.common.NResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final AuthApi authApi;
    private final AccountApi accountApi;
    private final PublicUriProperties publicUriProperties;
    private final ObjectMapper objectMapper;

    private static final String H_AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (publicUriProperties.isPublicUri(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader(H_AUTHORIZATION);
        if (Strings.isBlank(jwtToken)) {
            handleAuthenticationException(response, new BadCredentialsException("please provide Authorization in your request"));
            return;
        }

        String token = jwtToken.replace("Bearer", "").trim();

        try {
            SignedJWT signedJWT = authApi.parseAndVerifyJWT(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            String accountId = claimsSet.getSubject();
            String orgId = claimsSet.getStringClaim("orgId");
            String email = claimsSet.getStringClaim("email");

            AccountInfo accountInfo = AccountInfo.builder().accountId(accountId).email(email).orgId(Long.valueOf(orgId)).build();
            AccountInfoContext.set(accountInfo);
            filterChain.doFilter(request, response);
        } catch (ParseException | JOSEException e) {
            handleAuthenticationException(response, new BadCredentialsException(e.getMessage(), e));
        } catch (AuthenticationException e) {
            handleAuthenticationException(response, e);
        } finally {
            AccountInfoContext.remove();
        }
    }

    private void handleAuthenticationException(HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        NResponse<?> nResponse = new NResponse<>(401, e.getMessage(), null);
        response.getWriter().write(objectMapper.writeValueAsString(nResponse));
    }
}
