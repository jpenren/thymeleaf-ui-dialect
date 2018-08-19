package io.github.thymeleaf.ui.components;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Dropdown extends Component {

    public enum Direction {
        DROPUP, DROPRIGHT, DROPLEFT, DROPDOWN;
    }
    
    private final ElementCollection items = new ElementCollection();
    private final @Getter Toggle toggle;
    private @Getter Direction direction = Direction.DROPDOWN;

    public Dropdown header(String text) {
        items.add(Header.with(text));
        return this;
    }
    
    public Dropdown direction(Direction direction) {
        this.direction = direction;
        return this;
    }
    
    public Dropdown addLink(String href, String text) {
        return add(Link.with(href, text));
    }
    
    public Dropdown addLink(String href, String text, String title) {
        return add(Link.with(href, text, title));
    }
    
    public Dropdown add(Link link) {
        items.add(link);
        return this;
    }
    
    public Dropdown add(int index, Link link) {
        items.add(index, link);
        return this;
    }
    
    public Dropdown add(Link ... links) {
        this.items.addAll(links);
        return this;
    }
    
    public Dropdown add(int index, Link ... links) {
        this.items.addAll(index, links);
        return this;
    }
    
    public Dropdown divider() {
        items.add(new Divider());
        return this;
    }
    
    public Dropdown divider(int index) {
        items.add(index, new Divider());
        return this;
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    public static Dropdown with(String toggle) {
        return new Dropdown(Toggle.with(toggle));
    }
    
    public static Dropdown with(String toggle, String toggleId) {
        return new Dropdown(Toggle.with(toggle, toggleId));
    }
    
    @Getter
    @RequiredArgsConstructor
    public static class Toggle extends Element {
        private final String text;
        private String id;
        private String className;
        
        public static Toggle with(String text) {
            return new Toggle(text);
        }
        
        public static Toggle with(String text, String id) {
            return with(text, id, null);
        }
        
        public static Toggle with(String text, String id, String className) {
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
        
    }

}
