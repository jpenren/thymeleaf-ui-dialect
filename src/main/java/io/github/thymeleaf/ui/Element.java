package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * Base HTML element
 */
@Getter
@Setter
public abstract class Element implements VisibilityChecker, HasHtmlAttributes {
    private String id;
    private String className; // html class attribute

    public String getName() {
        final String simpleName = getClass().getSimpleName();
        return Strings.isNotEmpty(simpleName) ? simpleName : getClass().getSuperclass().getSimpleName();
    }

    @Override
    public boolean isVisible(HttpServletRequest request) {
        return true;
    }

}
