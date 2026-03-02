package com.nexxus.common;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

public class InstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String dateStr = jsonParser.getValueAsString();
        if (!StringUtils.hasText(dateStr)) {
            return null;
        }
        Date date = DateUtil.parse(dateStr, DatePattern.UTC_WITH_XXX_OFFSET_PATTERN);
        return date.toInstant();
    }
}
