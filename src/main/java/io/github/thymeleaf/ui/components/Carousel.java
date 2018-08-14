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

@Getter
@RequiredArgsConstructor
public class Carousel extends Component {
    private final List<Slide> slides = new ArrayList<>();
    private boolean showIndicators;
    private boolean showControls;
    
    public Carousel(String id) {
        setId(id);
    }
    
    public Carousel slide(String src) {
        slide(src, Strings.EMPTY);
        return this;
    }
    
    public Carousel slide(String src, String caption) {
        slide(new Image(src), caption);
        return this;
    }
    
    public Carousel slide(Image image) {
        slide(image, Strings.EMPTY);
        return this;
    }
    
    public Carousel slide(Image image, String caption) {
        Slide item = new Slide(image);
        item.setCaption(caption);
        this.slides.add(item);
        return this;
    }
    
    public Carousel showControls(){
        this.showControls = true;
        return this;
    }
    
    public Carousel showIndicators() {
        this.showIndicators = true;
        return this;
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
