package com.nexxus.cos.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.cos.task.TaskStatus;
import com.nexxus.common.handlers.UuidTypeHandler;
import com.nexxus.common.vo.Attachment;
import com.nexxus.cos.service.entity.handlers.JsonbAttachmentListTypeHandler;
import com.nexxus.cos.service.entity.handlers.JsonbStringListTypeHandler;
import com.nexxus.cos.service.entity.handlers.JsonbUuidListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.JdbcType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cos_task", autoResultMap = true)
public class TaskEntity extends BaseEntity {
    private Long orgId;
    private String displayId;
    private String title;
    private String shortDesc;
    private String longDesc;
    @TableField(typeHandler = UuidTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private UUID assignee;
    @TableField(typeHandler = JsonbUuidListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<UUID> participants;
    private Instant deadline;
    private TaskStatus status;
    @TableField(typeHandler = JsonbAttachmentListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<Attachment> attachments;
    @TableField(typeHandler = JsonbStringListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<String> relatedDeliverables;
}
