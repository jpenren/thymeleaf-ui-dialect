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
    
    public Carousel addSlide(String src) {
        return add(Slide.with(src));
    }
    
    public Carousel addSlide(String src, String caption) {
        return add(Slide.with(src, caption));
    }
    
    public Carousel add(Slide slide) {
        slides.add(slide);
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
        
        public static Slide with(String src) {
            return new Slide(new Image(src));
        }
        
        public static Slide with(String src, String caption) {
            Slide slide = new Slide(new Image(src));
            slide.setCaption(caption);
            return slide;
        }
        
    }
    
    public static Carousel empty(String id) {
        return new Carousel();
    }
    
    public static Carousel with(String id) {
        Carousel carousel = new Carousel();
        carousel.setId(id);
        return carousel;
    }
    
    public static Carousel with(Slide ... slides) {
        return with(null, slides);
    }
    
    public static Carousel with(String id, Slide ... slides) {
        Carousel carousel = new Carousel();
        carousel.setId(id);
        for (Slide slide : slides) {
            carousel.add(slide);
        }
        return carousel;
    }

}
