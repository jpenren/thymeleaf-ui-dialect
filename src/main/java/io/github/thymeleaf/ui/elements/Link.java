package io.github.thymeleaf.ui.elements;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.Urls;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link extends Element {
    private final @Getter(value = AccessLevel.NONE) String href;
    private final String text;
    private String title;
    private String target;
    private String rel;
    
    public Link(String href, String text) {
        Checks.checkIsNotEmpty(href);
        Checks.checkIsNotEmpty(text);
        this.href = href;
        this.text = text;
    }
    
    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }

    public static Link with(String href, String text) {
        return new Link(href, text);
    }

    public static Link with(String href, String text, String title) {
        Link link = new Link(href, text);
        link.title = title;
        return link;
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
