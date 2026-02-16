package com.nexxus.common;

import lombok.Data;

@Data
public class NexxusException extends RuntimeException {

    private final ErrorDefEnum errorDefEnum;

    public NexxusException(ErrorDefEnum errorDefEnum) {
        this(errorDefEnum, null);
    }

    public NexxusException(ErrorDefEnum errorDefEnum, Throwable e) {
        super(errorDefEnum.getDesc(), e);
        this.errorDefEnum = errorDefEnum;
    }
}
