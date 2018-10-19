package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.elements.Link;

public class Navigation extends Nav {
    
    public static Navigation empty() {
        return new Navigation();
    }
    
    public static Navigation with(Link...links) {
        Navigation navigation = new Navigation();
        navigation.add(links);
        return navigation;
    }

}
