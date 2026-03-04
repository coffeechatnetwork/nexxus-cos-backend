package com.nexxus.cos.api.dto.question;

import com.nexxus.common.enums.cos.question.QuestionCategory;
import com.nexxus.common.enums.cos.question.QuestionPriority;
import com.nexxus.common.enums.cos.question.QuestionStatus;
import com.nexxus.common.vo.Attachment;
import com.nexxus.cos.api.dto.user.UserDto;
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
public class QuestionDto implements Serializable {
    private Long orgId;
    private Long projectId;
    private String displayId;
    private String content;
    private QuestionCategory category;
    private QuestionPriority priority;
    private QuestionStatus status;
    private List<UserDto> assignees;
    private List<Attachment> attachments;
}
