package io.github.thymeleaf.ui.components;

import lombok.Getter;

public class Sidebar extends AbstractNavComponent<Sidebar> {
    private @Getter String header;

    public Sidebar header(String header) {
        this.header = header;
        return this;
    }
    
}
