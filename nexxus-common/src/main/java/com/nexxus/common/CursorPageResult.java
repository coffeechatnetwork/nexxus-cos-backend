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
public class CursorPageResult<T> implements Serializable {
    List<T> records;
    Long nextCursor;
    Boolean hasMore;
}
