package io.github.thymeleaf.ui.components;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper=false)
public class Badge extends Component {
    private final @Getter String href;
    private final String text;
    
    public Badge(String href, String text) {
        this.href = href;
        this.text = text;
    }
    
    public Badge(String text) {
        this(Strings.EMPTY, text);
    }
    
    public String getHref(HttpServletRequest request) {
        return request.getContextPath() + href;
    }
    
    public boolean isLink() {
        return Strings.isNotEmpty(href);
    }
    
    public boolean isText() {
        return Strings.isEmpty(href);
    }

}
