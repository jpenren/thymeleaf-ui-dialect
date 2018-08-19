package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Element;

class ElementCollection extends ArrayList<Element> implements Collection<Element> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElementCollection.class);
    private static final long serialVersionUID = 1L;

    public void addAll(Element ... elements) {
        Checks.checkNotNullArgument(elements);
        for (Element element : elements) {
            add(element);
        }
    }
    
    public void addAll(int index, Element ... elements) {
        Checks.checkNotNullArgument(elements);
        //prevent out of bounds
        if(index<0||index>size()) {
            LOGGER.warn("Array index out of bounds [index:{}, size:{}]", index, size());
            addAll(elements);
        }
        
        for (Element element : elements) {
            add(index, element);
        }
    }

}
