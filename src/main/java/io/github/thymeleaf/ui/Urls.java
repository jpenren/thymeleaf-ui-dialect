package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

public final class Urls {
    // Match if absolute uri (http://www.google.com | //www.google.com)
    private static final String ABSOLUTE_URI_REGEX = ".*//.*";

    private Urls() {
    }

    public static String resolve(String href, HttpServletRequest request) {
        if("#".equals(href)) {
            return href;
        }
        
        return href.matches(ABSOLUTE_URI_REGEX) ? href : request.getContextPath() + href;
    }

}
