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
    private Condition<HttpServletRequest> active = Conditions.value(false);
    private Condition<HttpServletRequest> disabled = Conditions.value(false);
    private Condition<HttpServletRequest> visible = Conditions.value(true);

    public String getName() {
        final String simpleName = getClass().getSimpleName();
        return Strings.isNotEmpty(simpleName) ? simpleName : getClass().getSuperclass().getSimpleName();
    }
    
    @Override
    public boolean isActive(HttpServletRequest request) {
        return active.test(request);
    }

    @Override
    public boolean isDisabled(HttpServletRequest request) {
        return disabled.test(request);
    }
    
    @Override
    public boolean isVisible(HttpServletRequest request) {
        return visible.test(request);
    }
    
}
