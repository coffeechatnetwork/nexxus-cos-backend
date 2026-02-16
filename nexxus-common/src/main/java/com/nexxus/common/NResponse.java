package com.nexxus.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NResponse<T> {
    public final Integer code;
    public final String desc;
    public final T data;

    public NResponse(T data) {
        this.code = 0;
        this.desc = "success";
        this.data = data;
    }

    public static <T> NResponse<T> success(T data) {
        return new NResponse<>(data);
    }
}
