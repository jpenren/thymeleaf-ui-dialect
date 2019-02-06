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
