package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * Base HTML element
 */
@Setter
public abstract class Element implements HasHtmlAttributes, HasStatus {
    private @Getter String id;
    private @Getter String className; // html class attribute
    
    public String getName() {
        final String simpleName = getClass().getSimpleName();
        return Strings.isNotEmpty(simpleName) ? simpleName : getClass().getSuperclass().getSimpleName();
    }
    
    @Override
    public boolean isActive(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean isDisabled(HttpServletRequest request) {
        return false;
    }
    
    @Override
    public boolean isVisible(HttpServletRequest request) {
        return true;
    }
    
}
