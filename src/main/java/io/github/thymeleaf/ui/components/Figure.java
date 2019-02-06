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
    
}
