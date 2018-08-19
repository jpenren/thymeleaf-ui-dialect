package io.github.thymeleaf.ui.components;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.Urls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Badge extends Component {
    private final @Getter String text;
    private String href;

    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }

    public boolean hasLink() {
        return Strings.isNotEmpty(href);
    }
    
    public static Badge with(String text) {
        return new Badge(text);
    }
    
    public static Badge with(String href, String text) {
        Badge badge = new Badge(text);
        badge.href = href;
        return badge;
    }

}
