/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.internal.Strings;
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
    
    public void setSlides(List<Slide> slides) {
        this.slides.addAll(slides);
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
