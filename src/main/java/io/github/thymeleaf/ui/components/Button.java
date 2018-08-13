package io.github.thymeleaf.ui.components;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.StatusChecker;
import io.github.thymeleaf.ui.Strings;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Button extends Component implements StatusChecker {
    private static final String DEFAULT_TYPE = "button";
    private final String text;
    private String type = DEFAULT_TYPE; //button, input, reset
    private boolean toggle;
    private String href;
    
    public Button(String text) {
        this.text=text;
    }
    
    public boolean isLink() {
        return Strings.isNotEmpty(href);
    }
    
    public boolean isButton() {
        return Strings.isEmpty(href);
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
