package com.nexxus.cos.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.cos.question.QuestionCategory;
import com.nexxus.common.enums.cos.question.QuestionPriority;
import com.nexxus.common.enums.cos.question.QuestionStatus;
import com.nexxus.common.vo.Attachment;
import com.nexxus.cos.service.entity.handlers.JsonbAttachmentListTypeHandler;
import com.nexxus.cos.service.entity.handlers.JsonbUuidListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cos_question", autoResultMap = true)
public class QuestionEntity extends BaseEntity {
    private Long orgId;
    private Long projectId;
    private String displayId;
    private String content;
    private QuestionPriority priority;
    private QuestionCategory category;
    private Long followUpId;
    private QuestionStatus status;
    @TableField(typeHandler = JsonbUuidListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<UUID> assignees;
    @TableField(typeHandler = JsonbAttachmentListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<Attachment> attachments;
}
