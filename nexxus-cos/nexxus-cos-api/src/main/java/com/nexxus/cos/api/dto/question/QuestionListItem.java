package com.nexxus.cos.api.dto.question;

import com.nexxus.common.enums.cos.question.QuestionCategory;
import com.nexxus.common.enums.cos.question.QuestionPriority;
import com.nexxus.common.enums.cos.question.QuestionStatus;
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
public class QuestionListItem implements Serializable {
    private Long orgId;
    private Long projectId;
    private String content;
    private QuestionPriority priority;
    private QuestionCategory category;
    private QuestionStatus status;
    private Instant createdAt;
    private ResponseDto lastestResponse;
}
