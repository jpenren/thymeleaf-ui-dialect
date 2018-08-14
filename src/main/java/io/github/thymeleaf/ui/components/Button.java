package io.github.thymeleaf.ui.components;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.StatusChecker;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.Urls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Button extends Component implements StatusChecker {
    public static final String DEFAULT_TYPE = "button";
    private final @Getter String text;
    private @Getter String type = DEFAULT_TYPE; // button, input, reset
    private @Getter boolean toggle;
    private String href;
    
    public Button(String text, String type) {
        this.text = text;
        this.type = type;
    }
    
    public Button href(String href) {
        this.href = href;
        return this;
    }

    public Button toggle() {
        this.toggle = true;
        return this;
    }

    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }
    
    public boolean hasLink() {
        return Strings.isNotEmpty(href);
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
