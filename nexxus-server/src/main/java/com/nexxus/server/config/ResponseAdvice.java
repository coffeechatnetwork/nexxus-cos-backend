package com.nexxus.server.config;

import com.nexxus.common.IgnoreResponseAdvice;
import com.nexxus.common.NResponse;
import com.nexxus.common.NexxusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "com.nexxus.server")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.hasMethodAnnotation(IgnoreResponseAdvice.class)) {
            return body;
        }
        if (body instanceof NResponse) {
            return body;
        }
        return NResponse.success(body);
    }

    @ExceptionHandler(NexxusException.class)
    public NResponse<?> demoException(NexxusException e) {
        log.error("Demo Exception", e);
        return new NResponse<>(e.getErrorDefEnum().getCode(), e.getErrorDefEnum().getDesc(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public NResponse<?> methodArgNotValidException(MethodArgumentNotValidException e) {
        log.error("Validate Exception", e);
        String msg = Optional.ofNullable(e.getBindingResult().getAllErrors())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(error -> {
                    String fieldName = error instanceof org.springframework.validation.FieldError
                            ? ((org.springframework.validation.FieldError) error).getField()
                            : error.getObjectName();
                    return fieldName + ": " + error.getDefaultMessage();
                })
                .distinct()
                .collect(Collectors.joining(", "));
        return new NResponse<>(400, msg, null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NResponse<?> badCredentialsException(BadCredentialsException e) {
        log.error("Bad Credentials Exception", e);
        return new NResponse<>(401, e.getMessage(), null);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NResponse<?> authenticationException(AuthenticationException e) {
        log.error("Authentication Exception", e);
        return new NResponse<>(401, "Unauthorized: Authentication required", null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public NResponse<?> illegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException Exception", e);
        return new NResponse<>(400, e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public NResponse<?> exception(Exception e) {
        log.error("Exception", e);
        return new NResponse<>(500, e.getMessage(), null);
    }
}
