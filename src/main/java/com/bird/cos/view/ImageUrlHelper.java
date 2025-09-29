package com.bird.cos.view;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 템플릿에서 이미지 경로를 일관되게 생성하기 위한 헬퍼.
 * 절대 URL은 그대로 사용하고, 상대 경로나 업로드 경로는 정규화한다.
 */
@Component("imageUrlHelper")
public class ImageUrlHelper {

    public String resolve(String raw) {
        return resolve(raw, null);
    }

    public String resolve(String raw, String fallback) {
        if (!StringUtils.hasText(raw)) {
            return fallback != null ? fallback : "";
        }

        String trimmed = raw.trim();
        if (isAbsoluteUrl(trimmed) || trimmed.startsWith("data:")) {
            return trimmed;
        }

        if (trimmed.startsWith("/images/uploaded/")) {
            return trimmed;
        }

        if (trimmed.startsWith("/")) {
            return trimmed;
        }

        if (trimmed.startsWith("images/uploaded/")) {
            return "/" + trimmed;
        }

        if (trimmed.startsWith("uploads/")) {
            return "/images/uploaded/" + trimmed.substring("uploads/".length());
        }

        if (trimmed.startsWith("/uploads/")) {
            return "/images/uploaded/" + trimmed.substring("/uploads/".length());
        }

        return "/" + trimmed;
    }

    private boolean isAbsoluteUrl(String value) {
        return value.startsWith("http://") || value.startsWith("https://");
    }
}
