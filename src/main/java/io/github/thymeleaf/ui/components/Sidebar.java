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

    public static Sidebar empty() {
        return new Sidebar();
    }

    public static Sidebar with(String header) {
        return with(header, new Link[0]);
    }

    public static Sidebar with(Link... links) {
        return with(null, links);
    }

    public static Sidebar with(String header, Link... links) {
        Sidebar sidebar = empty();
        sidebar.setHeader(header);
        if(links!=null) {
            sidebar.add(links);
        }
        
        return sidebar;
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
            return with(text, "menu-"+System.currentTimeMillis(), null, links);
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
