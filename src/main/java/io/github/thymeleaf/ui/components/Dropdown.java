package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Divider;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class Dropdown extends Component {
    
    public enum Direction{
        DROPUP, DROPRIGHT, DROPLEFT, DROPDOWN;
    }
    
    private final Toggle toggle;
    private final List<Element> items = new ArrayList<>();
    private @Setter Direction direction = Direction.DROPDOWN;
    
    public Dropdown header(String text) {
        this.items.add(new Header(text));
        return this;
    }
    
    public Dropdown divider() {
        this.items.add(new Divider());
        return this;
    }
    
    public Dropdown link(String href, String text) {
        link(new Link(href, text));
        return this;
    }
    
    public Dropdown link(Link item) {
        this.items.add(item);
        return this;
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Toggle extends Element {
        private final String text;
        private String id;
        private String className;
        private boolean asLink;
    }
    
    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Header extends Element {
        private final String text;
    }

}
