package com.nexxus.cos.api.dto.risklog;

import com.nexxus.common.enums.cos.risklog.RiskLogCategory;
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
public class RiskLogCategoryList implements Serializable {
    private RiskLogCategory category;
    private List<RiskLogListItem> items;
}
