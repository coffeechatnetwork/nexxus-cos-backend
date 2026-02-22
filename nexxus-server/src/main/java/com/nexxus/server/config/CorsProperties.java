package com.nexxus.server.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Configuration
public class CorsProperties {

    private Environment environment;
    private List<String> allowedOrigins = new ArrayList<>(List.of("http://localhost:3100"));
    private List<String> allowedMethods = List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
    private List<String> allowedHeaders = List.of("Authorization", "Content-Type", "X-Request-ID", "X-Requested-With");
    private boolean allowCredentials = true;
    private long maxAge = 3600L;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        // Support environment variable with comma-separated values
        // Environment variable takes precedence over configuration file
        if (environment == null) {
            return;
        }
        String envOrigins = environment.getProperty("CORS_ALLOWED_ORIGINS");
        if (envOrigins != null && !envOrigins.isEmpty()) {
            this.allowedOrigins = Arrays.stream(envOrigins.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
        }
    }
}