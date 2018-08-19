package io.github.thymeleaf.ui.components;

import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;

@SuppressWarnings("unchecked")
abstract class Nav<T> extends Component {
    private final ElementCollection items = new ElementCollection();
    
    public T addLink(String href, String text) {
        items.add(Link.with(href, text));
        return (T) this;
    }
    
    public T addLink(String href, String text, String title) {
        items.add(Link.with(href, text, title));
        return (T) this;
    }
    
    public T add(Link ... links) {
        items.addAll(links);
        return (T) this;
    }
    
    public T add(int index, Link ... links) {
        items.addAll(index, links);
        return (T) this;
    }
    
    public T add(Dropdown dropdown) {
        items.add(dropdown);
        return (T) this;
    }
    
    public T add(int index, Dropdown dropdown) {
        items.add(index, dropdown);
        return (T) this;
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }

}
