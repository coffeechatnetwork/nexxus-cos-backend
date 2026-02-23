package com.nexxus.auth.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrganizationRequest implements Serializable {
    @NotBlank
    @Size(max = 64)
    private String name;
    @NotBlank
    @Size(max = 32)
    private String code;
}
