package com.nexxus.cos.api.dto.question;

import com.nexxus.common.enums.cos.question.QuestionCategory;
import com.nexxus.common.enums.cos.question.QuestionPriority;
import com.nexxus.common.enums.cos.question.QuestionStatus;
import com.nexxus.common.vo.Attachment;
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
public class EditQuestionRequest implements Serializable {
    private QuestionPriority priority;
    private QuestionCategory category;
    private QuestionStatus status;
    private List<UUID> assignees;
    private List<Attachment> attachments;
}
