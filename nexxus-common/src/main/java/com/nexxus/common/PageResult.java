package com.nexxus.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageResult <T> implements Serializable {
    private List<T> records;
    private Long total;
    private Long pageSize;
    private Long page;
}
