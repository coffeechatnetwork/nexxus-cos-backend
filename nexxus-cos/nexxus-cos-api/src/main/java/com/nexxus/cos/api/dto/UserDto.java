package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.cos.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.net.URL;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String accountId;
    private String username;
    private String email;
    private URL avatarUrl;
    private UserStatus status;
}
