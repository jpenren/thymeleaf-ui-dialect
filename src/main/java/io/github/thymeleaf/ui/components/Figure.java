package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.elements.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Figure extends Component {
    private final Image image;
    private String caption;
    
    public Figure(String src) {
        this(src, null);
    }
    
    public Figure(String src, String alt) {
        this.image = new Image(src, alt);
    }
    
    public Figure caption(String caption) {
        this.caption = caption;
        return this;
    }
    
    public boolean hasCaption() {
        return Strings.isNotEmpty(caption);
    }
        
}
