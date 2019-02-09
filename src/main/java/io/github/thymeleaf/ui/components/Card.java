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
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Card extends Component {
    private final List<Link> links = new ArrayList<>();
    private Image image;
    private String header;
    private String title;
    private String subtitle;
    private String text;
    private String footer;
    
    public Card(Image image) {
        this.image = image;
    }
    
    public void addLink(String href, String text) {
        add(new Link(href, text));
    }
    
    public void add(Link link) {
        links.add(link);
    }
    
}
