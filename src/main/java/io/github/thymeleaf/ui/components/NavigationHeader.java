package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;

public class NavigationHeader extends Nav<NavigationHeader> {
    private @Getter Link brand;

    public NavigationHeader brand(String href, String text) {
        this.brand = new Link(href, text);
        return this;
    }

    public static NavigationHeader empty() {
        return new NavigationHeader();
    }

    public static NavigationHeader with(Link brand) {
        NavigationHeader navigationHeader = new NavigationHeader();
        navigationHeader.brand = brand;
        return navigationHeader;
    }
    
    public static NavigationHeader with(String href, String text) {
        return with(Link.with(href, text));
    }
    
    public static NavigationHeader with(String href, String text, Link...links) {
        return with(href, text).add(links);
    }

    public static NavigationHeader with(Link... links) {
        return new NavigationHeader().add(links);
    }

}
