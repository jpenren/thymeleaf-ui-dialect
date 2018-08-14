package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.elements.Link;

@SuppressWarnings("unchecked")
abstract class AbstractNavComponent<T> extends Component {
    private final List<Element> items = new ArrayList<>();
    
    public T link(Link link) {
        return add(link);
    }
    
    public T link(int index, Link link) {
        return add(index, link);
    }
    
    public T links(Link ... links) {
        Checks.checkNotNullArgument(links);
        items.addAll(Arrays.asList(links));
        return (T) this;
    }
    
    public T dropdown(Dropdown dropdown) {
        return add(dropdown);
    }
    
    public T menu(int index, Dropdown dropdown) {
        return add(index, dropdown);
    }
    
    private T add(Element element) {
        items.add(element);
        return (T) this;
    }
    
    private T add(int index, Element element) {
        if(index<0||index>items.size()) {
            return add(element);
        }
        
        items.add(index, element);
        return (T) this;
    }
    
    public List<Element> getItems() {
        return Collections.unmodifiableList(items);
    }
    
}
