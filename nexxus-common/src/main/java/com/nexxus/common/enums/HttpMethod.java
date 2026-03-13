package com.nexxus.common.enums;

import com.nexxus.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HttpMethod implements BaseEnum {
    GET("get", "Get"),
    POST("post", "Post"),
    ;

    private final String value;
    private final String desc;
}
