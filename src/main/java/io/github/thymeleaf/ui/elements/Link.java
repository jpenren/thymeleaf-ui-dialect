package io.github.thymeleaf.ui.elements;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.Urls;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Link extends Element {
    private final @NonNull @Getter(value = AccessLevel.NONE) String href;
    private final @NonNull String text;
    private String title;
    private String target;
    private String rel;
    
    public Link(String href, String text, String title) {
        this(href, text);
        this.title = title;
    }
    
    public Link title(String title) {
        this.title = title;
        return this;
    }
    
    public Link target(String target) {
        this.target = target;
        return this;
    }
    
    public Link rel(String rel) {
        this.rel = rel;
        return this;
    }

    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }

    public static Link with(String href, String text) {
        return new Link(href, text);
    }

    public static Link with(String href, String text, String title) {
        return new Link(href, text, title);
    }

    public static Link with(String href, String text, String title, String target) {
        return with(href, text, title, target, null);
    }

    public static Link with(String href, String text, String title, String target, String rel) {
        Link link = new Link(href, text);
        link.title = title;
        link.target = target;
        link.rel = rel;
        return link;
    }

}
