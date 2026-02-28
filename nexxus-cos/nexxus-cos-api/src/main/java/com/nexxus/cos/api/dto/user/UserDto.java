package com.nexxus.cos.api.dto.user;

import com.nexxus.common.enums.cos.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String accountId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String avatarUrl;
    private UserStatus status;
}
