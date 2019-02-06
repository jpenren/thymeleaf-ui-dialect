package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.elements.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Carousel extends Component {
    private final List<Slide> slides = new ArrayList<>();
    private boolean showIndicators;
    private boolean showControls;
    
    public void add(Slide slide) {
        slides.add(slide);
    }
    
    public List<Slide> getSlides() {
        return Collections.unmodifiableList(slides);
    }
    
    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Slide extends Element {
        private final Image image;
        private String caption;
        
        public boolean hasCaption() {
            return Strings.isNotEmpty(caption);
        }
        
    }

}
