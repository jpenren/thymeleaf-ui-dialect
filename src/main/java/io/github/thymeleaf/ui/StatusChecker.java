package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

public interface StatusChecker {

    boolean isActive(HttpServletRequest request);

    boolean isDisabled(HttpServletRequest request);
    
}
