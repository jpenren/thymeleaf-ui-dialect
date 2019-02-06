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

import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Dropdown extends Component {

    public enum Direction {
        DROPUP, DROPRIGHT, DROPLEFT, DROPDOWN;
    }
    
    private final ElementCollection items = new ElementCollection();
    private final Toggle toggle;
    private Direction direction = Direction.DROPDOWN;
    private Header header;
    
    public void setHeader(String header) {
        this.header = new Header(header);
    }
    
    public void add(Link ... links) {
        this.items.addAll(links);
    }
    
    public void add(int index, Link ... links) {
        this.items.addAll(index, links);
    }
    
    public void divider() {
        items.add(Divider.INSTANCE);
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    @Getter
    @RequiredArgsConstructor
    public static class Toggle extends Element {
        private final String text;
        
        public static Toggle toggle(String text) {
            return new Toggle(text);
        }
        
        public static Toggle toggle(String text, String id) {
            return toggle(text, id, null);
        }
        
        public static Toggle toggle(String text, String id, String className) {
            Toggle toggle = new Toggle(text);
            toggle.setId(id);
            toggle.setClassName(className);
            return toggle;
        }
    }
    
    @Getter
    @RequiredArgsConstructor
    public static class Header extends Element {
        private final String text;
        
        public static Header with(String text) {
            return new Header(text);
        }
    }
    
    public static class Divider extends Element {
        public static final Divider INSTANCE = new Divider();
    }

}
