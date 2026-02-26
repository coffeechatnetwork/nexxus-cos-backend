package com.nexxus.common.typehandlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nexxus.common.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JsonListTypeHandler<T> extends BaseTypeHandler<List<T>> {

    private final Class<T> elementType;

    public JsonListTypeHandler() {
        this.elementType = null;
    }

    public JsonListTypeHandler(Class<T> elementType) {
        this.elementType = elementType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        if (CollectionUtils.isEmpty(parameter)) {
            ps.setString(i, "[]");
            return;
        }
        ps.setString(i, JsonUtils.serialize(parameter));

    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonStr = rs.getString(columnName);
        return deserializeJson(jsonStr);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonStr = rs.getString(columnIndex);
        return deserializeJson(jsonStr);
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonStr  = cs.getString(columnIndex);
        return deserializeJson(jsonStr);
    }

    private List<T> deserializeJson(String inputStr) {
        if (!StringUtils.hasText(inputStr)) {
            return null;
        }
        try {
            if (elementType != null) {
                return JsonUtils.deserializeList(inputStr, elementType);
            }
            return JsonUtils.deserialize(inputStr, new TypeReference<List<T>>() {});
        } catch (Exception e) {
            String typeName = elementType != null ? elementType.getSimpleName() : "T";
            throw new IllegalArgumentException("Can't convert JSON to List<" + typeName + ">: " + inputStr, e);
        }
    }
}
