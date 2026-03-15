package com.nexxus.cos.api.dto.role;

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
public class CreateRoleRequest implements Serializable {
    @NotBlank
    @Size(max = 64)
    private String name;
    @Size(max = 255)
    private String description;
}
