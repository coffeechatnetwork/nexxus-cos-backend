package com.nexxus.server.config;

import com.nexxus.common.NexxusException;
import com.nexxus.common.IgnoreResponseAdvice;
import com.nexxus.common.NResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // intercept all request
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.hasMethodAnnotation(IgnoreResponseAdvice.class)) {
            return false;
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
                .map(ObjectError::getDefaultMessage)
                .distinct()
                .collect(Collectors.joining(", "));
        return new NResponse<>(400, msg, null);
    }

    @ExceptionHandler(Exception.class)
    public NResponse<?> exception(Exception e) {
        log.error("Exception", e);
        return new NResponse<>(500, e.getMessage(), null);
    }
}
