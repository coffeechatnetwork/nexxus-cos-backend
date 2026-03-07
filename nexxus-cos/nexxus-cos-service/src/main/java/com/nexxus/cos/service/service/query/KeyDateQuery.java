package com.nexxus.cos.service.service.query;

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
public class KeyDateQuery implements Serializable {
    private Long projectId;
    private Long page;
    private Long pageSize;
    private Instant startDate;
    private Instant endDate;
}

