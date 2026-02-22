package com.nexxus.auth.service.api;

import com.nexxus.auth.api.dto.LoginRequest;
import com.nexxus.auth.api.dto.RegisterRequest;
import com.nexxus.auth.service.service.JwtService;
import com.nexxus.common.NexxusException;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthResponse;
import com.nexxus.auth.service.entity.AccountEntity;
import com.nexxus.auth.service.service.AccountService;
import com.nexxus.common.enums.auth.AccountStatus;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.ParseException;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthApiImpl implements AuthApi {

    private final AccountService accountService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest req) {
        String email = req.getEmail();
        AccountEntity existingAccount = accountService.getByEmail(email);
        if (existingAccount != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("account already exist"));
        }

        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);

        String saltedPassword = req.getPassword() + salt;
        String pwdHash = passwordEncoder.encode(saltedPassword);

        AccountEntity accountEntity = AccountEntity.builder()
                .displayId(UUID.randomUUID().toString())
                .type(req.getType())
                .email(req.getEmail())
                .password(pwdHash)
                .salt(salt)
                .status(AccountStatus.ACTIVE)
                .orgId(req.getOrgId())
                .build();

        accountService.save(accountEntity);
        SignedJWT signedJWT;
        Long expiredInSeconds = 0L;
        try {
            signedJWT = jwtService.generateJWT(accountEntity.getDisplayId(), List.of("test"), req.getOrgId().toString(), req.getEmail());
            Instant expiredAt = signedJWT.getJWTClaimsSet().getExpirationTime().toInstant();
            expiredInSeconds = expiredAt.getEpochSecond() - Instant.now().getEpochSecond();
        } catch (JOSEException e) {
            throw new NexxusException(ErrorDefEnum.FAILED_TO_GENERATE_JWT, e);
        } catch (ParseException e) {
            throw new NexxusException(ErrorDefEnum.FAILED_TO_PARSE_JWT, e);
        }

        return AuthResponse.builder().token(signedJWT.serialize()).expiresInSeconds(expiredInSeconds).build();
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        String email = req.getEmail();
        AccountEntity accountEntity = accountService.getByEmail(email);
        if (accountEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("account not found"));
        }
        // check password
        String saltedPassword = req.getPassword() + accountEntity.getSalt();
        boolean matches = passwordEncoder.matches(saltedPassword, accountEntity.getPassword());
        if (!matches) {
            throw new BadCredentialsException("password in correct");
        }

        SignedJWT signedJWT;
        Long expiredInSeconds = 0L;
        try {
            signedJWT = jwtService.generateJWT(accountEntity.getDisplayId(), List.of("test"), accountEntity.getOrgId().toString(), req.getEmail());
            Instant expiredAt = signedJWT.getJWTClaimsSet().getExpirationTime().toInstant();
            expiredInSeconds = expiredAt.getEpochSecond() - Instant.now().getEpochSecond();
        } catch (JOSEException e) {
            throw new NexxusException(ErrorDefEnum.FAILED_TO_GENERATE_JWT, e);
        } catch (ParseException e) {
            throw new NexxusException(ErrorDefEnum.FAILED_TO_PARSE_JWT, e);
        }
        return AuthResponse.builder().token(signedJWT.serialize()).expiresInSeconds(expiredInSeconds).build();
    }
}
