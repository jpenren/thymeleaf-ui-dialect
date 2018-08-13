package io.github.thymeleaf.ui.dialect;

import java.util.Collections;

import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public final class UiTemplateResolver extends ClassLoaderTemplateResolver {
    
    public UiTemplateResolver(String theme) {
        setOrder(Integer.MAX_VALUE);
        setPrefix("/templates/ui/"+theme);
        setSuffix(".html");
        // Template name must match with '/components/template-name'
        setResolvablePatterns(Collections.singleton("/components/*"));
    }

}
