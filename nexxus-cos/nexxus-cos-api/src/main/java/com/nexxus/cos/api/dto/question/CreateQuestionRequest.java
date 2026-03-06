package com.nexxus.cos.api.dto.question;

import com.nexxus.common.enums.cos.question.QuestionCategory;
import com.nexxus.common.enums.cos.question.QuestionPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionRequest implements Serializable {
    @NotNull
    private Long projectId;
    @NotBlank
    private String content;
    @NotNull
    private QuestionCategory category;
    @NotNull
    private QuestionPriority priority;
    private Long followUpId;
    private List<UUID> assignees;
}
