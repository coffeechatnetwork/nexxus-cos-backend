package com.nexxus.auth.api.dto;

import com.nexxus.common.enums.auth.AppCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AppDto implements Serializable {
    private Long id;
    private String name;
    private AppCode code;
}
