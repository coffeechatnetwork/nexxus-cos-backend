package com.nexxus.cos.service.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nexxus.common.vo.Attachment;
import com.nexxus.cos.service.entity.handlers.JsonbAttachmentListTypeHandler;
import com.nexxus.cos.service.entity.handlers.JsonbStringListTypeHandler;
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
        registry.register(new TypeReference<List<String>>(){}.getClass(), JdbcType.OTHER, JsonbStringListTypeHandler.class);
        registry.register(JsonbStringListTypeHandler.class);

        registry.register(new TypeReference<List<Attachment>>(){}.getClass(), JdbcType.OTHER, JsonbAttachmentListTypeHandler.class);
        registry.register(JsonbAttachmentListTypeHandler.class);
    }
}
