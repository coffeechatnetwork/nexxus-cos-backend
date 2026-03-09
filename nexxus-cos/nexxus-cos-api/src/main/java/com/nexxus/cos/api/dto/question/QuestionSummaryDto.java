package com.nexxus.cos.api.dto.question;

import com.nexxus.common.enums.cos.question.QuestionPriority;
import com.nexxus.common.enums.cos.question.QuestionStatus;
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
public class QuestionSummaryDto implements Serializable {
    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrioritySummaryItem {

        private QuestionPriority priority;

        private Map<QuestionStatus, Integer> statusCount;

    }

    private List<PrioritySummaryItem> priorities;
}
