package io.github.thymeleaf.ui.elements;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.StatusChecker;
import io.github.thymeleaf.ui.Urls;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Link extends Element implements StatusChecker {
    private @NonNull @Getter(value = AccessLevel.NONE) final String href;
    private @NonNull final String text;
    private String title;
    private String target;
    private String rel;

    public Link(String href, String text, String title) {
        this.href = href;
        this.text = text;
        this.title = title;
    }
    
    public Link(String href, String text, String title, String target) {
        this.href = href;
        this.text = text;
        this.title = title;
        this.target = target;
    }

    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }

    @Override
    public boolean isActive(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean isDisabled(HttpServletRequest request) {
        return false;
    }

}
