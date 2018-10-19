package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavigationHeader extends Nav {
    private Link brand;

    public static NavigationHeader empty() {
        return new NavigationHeader();
    }
    
    public static NavigationHeader with(String href, String text) {
        return with(href, text, new Link[0]);
    }
    
    public static NavigationHeader with(Link... links) {
        return with(Strings.EMPTY, Strings.EMPTY, links);
    }
    
    public static NavigationHeader with(String href, String text, Link...links) {
        NavigationHeader navigationHeader = new NavigationHeader();
        navigationHeader.setBrand(Link.with(href, text));
        if( links!=null ) {
            navigationHeader.add(links);
        }
        return navigationHeader;
    }

}
