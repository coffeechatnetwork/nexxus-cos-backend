package com.nexxus.auth.service.api;

import com.nexxus.auth.api.AuthApi;
import com.nexxus.auth.api.dto.AuthResponse;
import com.nexxus.auth.api.dto.LoginRequest;
import com.nexxus.auth.api.dto.RegisterRequest;
import com.nexxus.auth.service.entity.AccountEntity;
import com.nexxus.auth.service.entity.AppEntity;
import com.nexxus.auth.service.service.AccountService;
import com.nexxus.auth.service.service.AppService;
import com.nexxus.auth.service.service.JwtService;
import com.nexxus.common.ErrorDefEnum;
import com.nexxus.common.NexxusException;
import com.nexxus.common.enums.auth.AccountStatus;
import com.nexxus.common.enums.auth.AppCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.ParseException;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthApiImpl implements AuthApi {

    private final AccountService accountService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AppService appService;

    @Override
    public AuthResponse register(RegisterRequest req) {
        String email = req.getEmail();
        Long orgId = req.getOrgId();
        AppCode appCode = req.getAppCode();
        AppEntity appEntity = appService.getByCode(appCode);
        if (appEntity == null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("app already exist"));
        }
        AccountEntity existingAccount = accountService.getByAppCodeAndEmail(appCode, email);
        if (existingAccount != null) {
            throw new NexxusException(ErrorDefEnum.RESOURCE_CONFLICT.desc("account already exist"));
        }

        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);

        String saltedPassword = req.getPassword() + salt;
        String pwdHash = passwordEncoder.encode(saltedPassword);

        String displayId = Optional.ofNullable(req.getDisplayId())
                .filter(id -> !Strings.isBlank(id))
                .orElse(UUID.randomUUID().toString());

        AccountEntity accountEntity = AccountEntity.builder()
                .displayId(displayId)
                .type(req.getType())
                .email(req.getEmail())
                .password(pwdHash)
                .salt(salt)
                .status(AccountStatus.ACTIVE)
                .appCode(appCode)
                .build();

        accountService.save(accountEntity);
        SignedJWT signedJWT;
        Long expiredInSeconds = 0L;
        try {
            signedJWT = jwtService.generateJWT(accountEntity.getDisplayId(), List.of("test"), orgId.toString(), req.getEmail());
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
        Long orgId = req.getOrgId();
        AppCode appCode = req.getAppCode();
        AccountEntity accountEntity = accountService.getByAppCodeAndEmail(appCode, email);
        if (accountEntity == null) {
            throw new NexxusException(ErrorDefEnum.NOT_FOUND_EXCEPTION.desc("account not found"));
        }
        // check status
        if (accountEntity.getStatus() != AccountStatus.ACTIVE) {
            throw new NexxusException(ErrorDefEnum.FORBIDDEN.desc("you account is not active"));
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
            signedJWT = jwtService.generateJWT(accountEntity.getDisplayId(), List.of("test"), orgId.toString(), req.getEmail());
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
    public SignedJWT parseAndVerifyJWT(String token) throws ParseException, JOSEException {
        return jwtService.parseAndVerifyJWT(token);
    }
}
