package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Divider;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;

public class Navigation extends Component {
    private final List<Element> items = new ArrayList<>();
    
    public Navigation link(String href, String text) {
        link(new Link(href, text));
        return this;
    }
    
    public Navigation link(Link item) {
        items.add(item);
        return this;
    }
    
    public Navigation dropdown(DropdownItem dropdown) {
        items.add(dropdown);
        return this;
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
    public static class DropdownItem extends Link {
        private final @Getter List<Element> items = new ArrayList<>();
        
        public DropdownItem item(String href, String text) {
            items.add(new Link(href, text));
            return this;
        }
        
        public DropdownItem divider() {
            items.add(new Divider());
            return this;
        }

        public DropdownItem(String text) {
            super("#", text);
        }
        
    }

}
