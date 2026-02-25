package com.nexxus.common;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
public class AccountInfo implements Serializable {
    private String accountId;
    private String email;
    private Long orgId;
}
