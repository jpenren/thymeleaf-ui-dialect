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

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Sidebar extends Component {
    private final ElementCollection items = new ElementCollection();
    private String header;

    public void add(Link... links) {
        items.addAll(links);
    }

    public void add(int index, Link... links) {
        items.addAll(index, links);
    }

    public void add(Submenu... submenus) {
        items.addAll(submenus);
    }

    public void add(int index, Submenu... submenus) {
        items.addAll(index, submenus);
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    @RequiredArgsConstructor
    public static class Submenu extends Element {
        private final ElementCollection items = new ElementCollection();
        private final @Getter String text;
        
        public Submenu(String text, String id) {
            this(text);
            this.setId(id);
        }
        
        public Submenu(String text, String id, Link ...links) {
            this(text, id);
            this.add(links);
        }
        
        public Submenu add(Link... links) {
            items.addAll(links);
            return this;
        }

        public Submenu add(int index, Link... links) {
            items.addAll(index, links);
            return this;
        }
        
        public List<Element> getItems() {
            return Collections.unmodifiableList(items);
        }
        
        public boolean isExpanded(HttpServletRequest request) {
            return false;
        }

    }

}
