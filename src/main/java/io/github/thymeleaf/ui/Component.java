package io.github.thymeleaf.ui;

import lombok.Getter;
import lombok.Setter;

/**
 * Any HTML element with template
 */
@Getter
@Setter
public abstract class Component extends Element implements Renderable {
    private String template = defaultTemplate();

    // Default template name: ClassName to dash => class-name
    private String defaultTemplate() {
        final String simpleName = getClass().getSimpleName();
        final boolean isAnonimous = Strings.isEmpty(simpleName);
        final String className = isAnonimous ? getClass().getSuperclass().getSimpleName() : simpleName;
        
        return "/components/" + Strings.dash(className);
    }

}
