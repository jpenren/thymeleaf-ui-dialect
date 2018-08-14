package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;

public class NavigationHeader extends AbstractNavComponent<NavigationHeader> {
    private @Getter Link brand;
    
    public NavigationHeader brand(String href, String text) {
        this.brand = new Link(href, text);
        return this;
    }
    
}
