package com.nexxus.cos.api.dto.deliverable;

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
public class DeliverableDashboardRequest implements Serializable {
    @NotNull
    private Instant startDate;
    private Instant endDate;
    @NotNull
    private Long projectId;
}
