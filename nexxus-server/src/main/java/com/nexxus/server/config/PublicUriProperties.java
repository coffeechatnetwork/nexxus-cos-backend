package com.nexxus.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

@Configuration
public class PublicUriProperties {
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private static final String[] PUBLIC_URI_PATTERNS = new String[]{
            "/actuator/**",
            "/v1/auth/**",
            "/v1/apps/*/register",
            "/v1/apps/*/login",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api-docs/**",
            "/error"

    };

    public Boolean isPublicUri(String uri) {
        for (String pattern : PUBLIC_URI_PATTERNS) {
            if (PATH_MATCHER.match(pattern, uri)) {
                return true;
            }
        }
        return false;
    }
}
