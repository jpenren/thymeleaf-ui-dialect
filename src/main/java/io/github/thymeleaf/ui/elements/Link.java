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

}
