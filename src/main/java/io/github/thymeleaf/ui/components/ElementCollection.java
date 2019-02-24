/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.internal.Checks;

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
