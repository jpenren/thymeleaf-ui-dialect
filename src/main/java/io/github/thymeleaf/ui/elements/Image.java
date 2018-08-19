package io.github.thymeleaf.ui.elements;

import io.github.thymeleaf.ui.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Image extends Element {
    private final String src;
    private String alt;
    private String title;
    
    public static Image with(String src) {
        return new Image(src);
    }
    
    public static Image with(String src, String alt) {
        return new Image(src, alt, null);
    }
    
    public static Image with(String src, String alt, String title) {
        return new Image(src, alt, title);
    }

}
