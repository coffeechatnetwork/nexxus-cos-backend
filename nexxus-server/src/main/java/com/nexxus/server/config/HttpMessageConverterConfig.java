package com.nexxus.server.config;

import com.nexxus.common.BaseEnum;
import com.nexxus.common.BaseEnumDeserializer;
import com.nexxus.common.BaseEnumSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class HttpMessageConverterConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(BaseEnum.class, new BaseEnumSerializer());
        simpleModule.addDeserializer(BaseEnum.class, new BaseEnumDeserializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(simpleModule);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        converters.removeIf(MappingJackson2HttpMessageConverter.class::isInstance);
        converters.add(0, converter);
    }
}
