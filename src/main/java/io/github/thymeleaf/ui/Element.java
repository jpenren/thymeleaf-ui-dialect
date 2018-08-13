package io.github.thymeleaf.ui;

/**
 * Basic HTML element without template (Link, Image)
 */
public abstract class Element {
    
    public String getName() {
        final String simpleName = getClass().getSimpleName();
        return Strings.isNotEmpty(simpleName) ? simpleName : getClass().getSuperclass().getSimpleName();
    }
   
}
