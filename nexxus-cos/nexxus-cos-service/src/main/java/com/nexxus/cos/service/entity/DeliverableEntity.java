package com.nexxus.cos.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.cos.deliverable.DeliverableStatus;
import com.nexxus.common.vo.Attachment;
import com.nexxus.cos.service.entity.handlers.JsonbAttachmentListTypeHandler;
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
@TableName("cos_deliverable")
public class DeliverableEntity extends BaseEntity {
    private String displayId;
    private String title;
    private String shortDesc;
    private String longDesc;
    private UUID assignee;
    @TableField(typeHandler = JsonbUuidListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<UUID> participants;
    private Instant deadline;
    private DeliverableStatus status;
    @TableField(typeHandler = JsonbAttachmentListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<Attachment> attachments;
}
