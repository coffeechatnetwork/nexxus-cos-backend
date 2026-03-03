package com.nexxus.cos.api.dto.checklist;

import com.nexxus.common.enums.cos.checklist.DevChecklistCategory;
import com.nexxus.common.enums.cos.checklist.DevChecklistStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DevChecklistSummaryDto implements Serializable {
    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategorySummaryItem {

        private DevChecklistCategory category;

        private Map<DevChecklistStatus, Integer> statusCount;

    }

    private List<CategorySummaryItem> categories;
}
