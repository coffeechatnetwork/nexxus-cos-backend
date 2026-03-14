package com.nexxus.common.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class JsonUtils {

    private static final ObjectMapper MAPPER = new JsonMapper();

    public JsonUtils() {
    }

    static {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Enum.class, new BaseEnumSerializer());
        simpleModule.addDeserializer(Enum.class, new BaseEnumDeserializer());

        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        MAPPER.registerModule(simpleModule);
    }

    public static String serialize(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(String inputStr, Class<T> tClass) {
        try {
            return MAPPER.readValue(inputStr, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Failed to deserialize JSON to %s", tClass.getSimpleName()), e);
        }
    }

    public static <T> T deserialize(String inputStr, TypeReference<T> tTypeReference) {
        try {
            return MAPPER.readValue(inputStr, tTypeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Failed to deserialize JSON to %s", tTypeReference.getType().getTypeName()), e);
        }
    }

    public static <T> List<T> deserializeList(String inputStr, Class<T> tClass) {
        CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(List.class, tClass);
        try {
            return MAPPER.readValue(inputStr, collectionType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("deserialize list failed", e);
        }
    }

}
