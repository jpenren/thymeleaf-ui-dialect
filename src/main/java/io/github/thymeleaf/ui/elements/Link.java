package io.github.thymeleaf.ui.elements;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.StatusChecker;
import io.github.thymeleaf.ui.VisibilityChecker;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class Link extends Element implements VisibilityChecker, StatusChecker {
    
    //Match if absolute uri (http://www.google.com | //www.google.com)
    private static final String ABSOLUTE_URI_REGEX = ".*//.*";
    private @NonNull @Getter(value=AccessLevel.NONE) final String href;
    private @NonNull final String text;
    private String title;
    private String target;
    private String rel;
    
    public String getHref(HttpServletRequest request) {
        return href.matches(ABSOLUTE_URI_REGEX) ? href : request.getContextPath()+href;
    }
    
    @Override
    public boolean isActive(HttpServletRequest request) {
        return false;
    }
    @Override
    public boolean isDisabled(HttpServletRequest request) {
        return false;
    }
    @Override
    public boolean isVisible(HttpServletRequest request) {
        return true;
    }

}
