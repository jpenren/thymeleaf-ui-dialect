package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Dropdown extends Component {

    public enum Direction {
        DROPUP, DROPRIGHT, DROPLEFT, DROPDOWN;
    }
    
    private final List<Element> items = new ArrayList<>();
    private final @Getter Toggle toggle;
    private @Getter @Setter Direction direction = Direction.DROPDOWN;

    public Dropdown(String text) {
        this.toggle = new Toggle(text);
    }
    
    public Dropdown(String id, String text) {
        this.toggle = new Toggle(id, text);
    }
    
    public Dropdown header(String text) {
        return add(new Header(text));
    }
    
    public Dropdown link(Link link) {
        return add(link);
    }
    
    public Dropdown link(int index, Link link) {
        return add(index, link);
    }
    
    public Dropdown links(Link ... links) {
        Checks.checkNotNullArgument(links);
        this.items.addAll(Arrays.asList(links));
        return this;
    }
    
    public Dropdown links(int index, Link ... links) {
        //prevent out of range
        if(index<0||index>items.size()) {
            return links(links);
        }
        
        Checks.checkNotNullArgument(links);
        this.items.addAll(index, Arrays.asList(links));
        return this;
    }
    
    public Dropdown divider() {
        return add(new Divider());
    }
    
    public Dropdown divider(int index) {
        return add(index, new Divider());
    }
    
    public boolean isExpanded(HttpServletRequest request) {
        return false;
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    private Dropdown add(Element element) {
        items.add(element);
        return this;
    }
    
    private Dropdown add(int index, Element element) {
        //prevent out of range
        if(index<0||index>items.size()) {
            return add(element);
        }
        
        items.add(index, element);
        return this;
    }

    
    @Getter
    @RequiredArgsConstructor
    public static class Toggle extends Element {
        private final String text;
        private String id;
        private String className;
        
        public Toggle(String id, String text) {
            this.id = id;
            this.text = text;
        }
        
        public Toggle(String id, String text, String className) {
            this.id = id;
            this.text = text;
            this.className = className;
        }
    }
    
    @Getter
    @RequiredArgsConstructor
    public static class Header extends Element {
        private final String text;
    }
    
    public static class Divider extends Element {
        
    }

}
