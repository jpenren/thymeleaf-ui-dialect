package io.github.thymeleaf.ui.components;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Sidebar extends Component {
    private final ElementCollection items = new ElementCollection();
    private @Getter String header;

    public Sidebar header(String header) {
        this.header = header;
        return this;
    }

    public Sidebar add(Link... links) {
        items.addAll(links);
        return this;
    }

    public Sidebar add(int index, Link... links) {
        items.addAll(index, links);
        return this;
    }

    public Sidebar add(Submenu... submenus) {
        items.addAll(submenus);
        return this;
    }

    public Sidebar add(int index, Submenu... submenus) {
        items.addAll(index, submenus);
        return this;
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }

    public static Sidebar empty() {
        return new Sidebar();
    }

    public static Sidebar with(String header) {
        return new Sidebar().header(header);
    }

    public static Sidebar with(Link... links) {
        return empty().add(links);
    }

    public static Sidebar with(String header, Link... links) {
        return with(header).add(links);
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

        public static Submenu with(String text, Link... links) {
            return with(text, null, null, links);
        }
        
        public static Submenu with(String text, String id, Link... links) {
            return with(text, id, null, links);
        }
        
        public static Submenu with(String text, String id, String className, Link... links) {
            Submenu submenu = new Submenu(text, id, links);
            submenu.setClassName(className);
            return submenu;
        }
    }

}
