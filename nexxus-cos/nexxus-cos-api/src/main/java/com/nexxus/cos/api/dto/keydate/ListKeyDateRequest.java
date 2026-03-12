package com.nexxus.cos.api.dto.keydate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class ListKeyDateRequest implements Serializable {
    @NotNull
    @Min(value = 1)
    private Long page;
    @NotNull
    @Max(value = 50)
    private Long pageSize;
    private Instant startDate;
    private Instant endDate;
}
