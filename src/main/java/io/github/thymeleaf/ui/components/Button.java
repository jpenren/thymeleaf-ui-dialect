package io.github.thymeleaf.ui.components;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.Urls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Button extends Component {
    public static final String DEFAULT_TYPE = "button";
    private final @Getter String text;
    private @Getter String type = DEFAULT_TYPE; // button, input, reset
    private @Getter boolean toggle;
    private String href;
    
    public Button setToggle() {
        this.toggle = true;
        return this;
    }

    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }
    
    public boolean hasLink() {
        return Strings.isNotEmpty(href);
    }
    
    public static Button with(String text) {
        return new Button(text);
    }
    
    public static Button toggle(String text) {
        return new Button(text).setToggle();
    }
    
    public static Button with(String text, String type) {
        Button button = new Button(text);
        button.type = type;
        return button;
    }
    
    public static Button asLink(String href, String text) {
        Button button = new Button(text);
        button.href = href;
        return button;
    }

}
