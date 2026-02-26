package com.nexxus.cos.service.entity.handlers;

import com.nexxus.common.handlers.JsonListTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.OTHER)
public class JsonStringListTypeHandler extends JsonListTypeHandler<String> {
    public JsonStringListTypeHandler() {
        super(String.class);
    }
}
