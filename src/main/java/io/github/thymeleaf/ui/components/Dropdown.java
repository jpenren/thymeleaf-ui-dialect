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
        this.header = Header.with(header);
    }
    
//    
//    public Dropdown addLink(String href, String text) {
//        return add(Link.with(href, text));
//    }
//    
//    public Dropdown addLink(String href, String text, String title) {
//        return add(Link.with(href, text, title));
//    }
    
//    public void add(Link item) {
//        items.add(item);
//    }
//    
//    public void add(int index, Link item) {
//        items.add(index, item);
//    }
    
    public void add(Link ... items) {
        this.items.addAll(items);
    }
    
    public void add(int index, Link ... items) {
        this.items.addAll(index, items);
    }
    
    public void divider() {
        items.add(Divider.INSTANCE);
    }
//    
//    public Dropdown divider(int index) {
//        items.add(index, new Divider());
//        return this;
//    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    public static Dropdown with(String toggle) {
        return with(toggle, null);
    }
    
//    public static Dropdown with(String toggle, Header header) {
//        return with(toggle, null);
//    }
    
    public static Dropdown with(String toggle, String toggleId) {
        return new Dropdown(Toggle.with(toggle, toggleId));
    }
    
//    public static Dropdown with(String toggle, String toggleId) {
//        return new Dropdown(Toggle.with(toggle, toggleId));
//    }
    
    @Getter
    @RequiredArgsConstructor
    public static class Toggle extends Element {
        private final String text;
        
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
        public static final Divider INSTANCE = new Divider();
    }

}
