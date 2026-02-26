package com.nexxus.cos.service.config;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nexxus.common.handlers.JsonListTypeHandler;
import com.nexxus.cos.service.entity.handlers.JsonStringListTypeHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MyBatisPlusConfig {

    private final SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void registerTypeHandlers() {
        TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
        registry.register(new TypeReference<List<String>>(){}.getClass(), JdbcType.OTHER, JsonStringListTypeHandler.class);
        registry.register(JsonStringListTypeHandler.class);
    }
}
