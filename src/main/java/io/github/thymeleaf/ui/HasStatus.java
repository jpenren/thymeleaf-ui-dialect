package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

public interface HasStatus {

    boolean isActive(HttpServletRequest request);

    boolean isDisabled(HttpServletRequest request);

    boolean isVisible(HttpServletRequest request);
}
