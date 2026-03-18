package com.nexxus.cos.service.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UrlPatternMatcher {

    public Pattern compilePattern(String urlPattern) {
        if (urlPattern == null || urlPattern.isEmpty()) {
            return null;
        }

        // Escape special characters, but keep * as wildcard
        String regex = urlPattern
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\*", "[^/]+");

        return Pattern.compile("^" + regex + "$");
    }

    public boolean matches(String urlPattern, String requestUrl, String requestMethod) {
        if (urlPattern == null || requestUrl == null || requestMethod == null) {
            return false;
        }

        Pattern pattern = compilePattern(urlPattern);
        return pattern != null && pattern.matcher(requestUrl).matches();
    }

    public Long extractProjectId(String url, String pattern) {
        if (url == null || pattern == null) {
            return null;
        }

        String[] urlParts = url.split("/");
        String[] patternParts = pattern.split("/");

        for (int i = 0; i < Math.min(patternParts.length, urlParts.length); i++) {
            if ("*".equals(patternParts[i])) {
                try {
                    return Long.parseLong(urlParts[i]);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }

        return null;
    }
}
