package com.nexxus.cos.service.entity.handlers;

import com.nexxus.common.handlers.JsonbListTypeHandler;
import com.nexxus.common.vo.Attachment;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.OTHER)
public class JsonbAttachmentListTypeHandler extends JsonbListTypeHandler<Attachment> {
    public JsonbAttachmentListTypeHandler () {
        super(Attachment.class);
    }
}
