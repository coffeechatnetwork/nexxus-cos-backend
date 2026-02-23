package com.nexxus.auth.api.dto;

import com.nexxus.common.enums.auth.OrganizationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto implements Serializable {
    private String displayId;
    private String name;
    private String code;
    private OrganizationStatus status;
}
