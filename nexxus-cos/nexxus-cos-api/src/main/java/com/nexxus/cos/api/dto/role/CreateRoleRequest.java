package com.nexxus.cos.api.dto.role;

import com.nexxus.common.enums.cos.role.Role;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Role name;
    @Size(max = 255)
    private String description;
}
