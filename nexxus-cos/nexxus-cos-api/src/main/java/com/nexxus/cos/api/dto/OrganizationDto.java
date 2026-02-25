package com.nexxus.cos.api.dto;

import com.nexxus.common.enums.cos.organization.OrganizationStatus;
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
