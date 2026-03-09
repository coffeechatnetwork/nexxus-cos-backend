package com.nexxus.cos.api.dto.question;

import com.nexxus.common.enums.cos.question.ResponseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateResponseRequest implements Serializable {
    @NotNull
    private Long questionId;
    @NotBlank
    private String content;
    @NotNull
    private ResponseStatus status;
}
