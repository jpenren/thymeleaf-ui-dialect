package io.github.thymeleaf.ui.components;

import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;

abstract class Nav extends Component {
    private final ElementCollection items = new ElementCollection();
    
    public void add(Link ... links) {
        items.addAll(links);
    }
    
    public void add(int index, Link ... links) {
        items.addAll(index, links);
    }
    
    public void add(Dropdown dropdown) {
        items.add(dropdown);
    }
    
    public void add(int index, Dropdown dropdown) {
        items.add(index, dropdown);
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }

}
