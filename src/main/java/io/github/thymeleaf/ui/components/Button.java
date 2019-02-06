package io.github.thymeleaf.ui.components;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.Urls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
public class Button extends Component {
    public static final String DEFAULT_TYPE = "button";
    private final @Getter String text;
    private @Getter String type = DEFAULT_TYPE; // button, input, reset
    private @Getter boolean toggle;
    private String href;

    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }
    
    public boolean hasLink() {
        return Strings.isNotEmpty(href);
    }
    
}
