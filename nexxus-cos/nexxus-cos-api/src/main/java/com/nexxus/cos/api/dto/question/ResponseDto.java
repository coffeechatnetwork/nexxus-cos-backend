package com.nexxus.cos.api.dto.question;

import com.nexxus.common.enums.cos.question.ResponseStatus;
import com.nexxus.cos.api.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable {
    private String content;
    private ResponseStatus status;
    private UserDto createdBy;
    private Instant createdAt;
}
