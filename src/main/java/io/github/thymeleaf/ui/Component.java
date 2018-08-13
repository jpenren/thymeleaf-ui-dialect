package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Component implements Renderable, HasHtmlAttributes, VisibilityChecker {
    private String template = defaultTemplate();
    private String id;
    private String className; //html class attribute
    
    @Override
    public boolean isVisible(HttpServletRequest request) {
        return true;
    }
    
    // Default template name: ClassName to dash => class-name
    private String defaultTemplate() {
        final String simpleName = getClass().getSimpleName();
        final boolean isAnonimous = Strings.isEmpty(simpleName);
        final String className = isAnonimous ? getClass().getSuperclass().getSimpleName() : simpleName;
        
        return "/components/" + Strings.dash(className);
    }
    
}
