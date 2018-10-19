package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.elements.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Figure extends Component {
    private final Image image;
    private String caption;
    
    public boolean hasCaption() {
        return Strings.isNotEmpty(caption);
    }
    
    public static Figure with(Image image) {
        return new Figure(image);
    }
    
    public static Figure with(String src) {
        return new Figure(Image.with(src));
    }
    
    public static Figure with(String src, String alt) {
        return new Figure(Image.with(src, alt));
    }
    
    public static Figure with(String src, String alt, String title) {
        return new Figure(Image.with(src, alt, title));
    }
        
}
