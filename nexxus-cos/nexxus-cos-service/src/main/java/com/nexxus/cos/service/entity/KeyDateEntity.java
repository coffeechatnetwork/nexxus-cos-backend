package com.nexxus.cos.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nexxus.common.BaseEntity;
import com.nexxus.common.enums.cos.keydate.KeyDateCategory;
import com.nexxus.common.vo.Attachment;
import com.nexxus.cos.service.entity.handlers.JsonbAttachmentListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.JdbcType;

import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("cos_key_date")
public class KeyDateEntity extends BaseEntity {
    private Long orgId;
    private Long projectId;
    private String title;
    private String shortDesc;
    private String longDesc;
    private KeyDateCategory category;
    private Instant referenceDate;
    @TableField(typeHandler = JsonbAttachmentListTypeHandler.class, jdbcType = JdbcType.OTHER)
    private List<Attachment> attachments;
}
