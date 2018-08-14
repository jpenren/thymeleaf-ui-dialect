package io.github.thymeleaf.ui.components;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.Urls;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
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

}
