package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

public interface VisibilityChecker {

    boolean isVisible(HttpServletRequest request);
    
}
