package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.elements.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Figure extends Component {
    private final Image image;
    private String caption;
    
    public Figure(String src) {
        this(src, null);
    }
    
    public Figure(String src, String alt) {
        this.image = new Image(src, alt);
    }
    
    public boolean hasCaption() {
        return Strings.isNotEmpty(caption);
    }
        
}
