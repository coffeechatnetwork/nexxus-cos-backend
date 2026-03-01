package com.nexxus.cos.service.entity.handlers;

import com.nexxus.common.handlers.JsonbListTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;
import java.util.UUID;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.OTHER)
public class JsonbUuidListTypeHandler extends JsonbListTypeHandler<UUID> {

    public JsonbUuidListTypeHandler() {
        super(UUID.class);
    }
}
