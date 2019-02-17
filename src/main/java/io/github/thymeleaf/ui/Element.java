/*
 * Copyright 2002-2015 the original author or authors.
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

package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.internal.Strings;
import lombok.Getter;
import lombok.Setter;

/**
 * Base HTML element
 */
@Setter
public abstract class Element implements HasHtmlAttributes, HasStatus {
    private @Getter String id;
    private @Getter String className; // html class attribute
    private boolean active;
    private boolean disabled;
    private boolean visible = true;
    
    public String getName() {
        final String simpleName = getClass().getSimpleName();
        return Strings.isNotEmpty(simpleName) ? simpleName : getClass().getSuperclass().getSimpleName();
    }
    
    @Override
    public boolean isActive(HttpServletRequest request) {
        return active;
    }

    @Override
    public boolean isDisabled(HttpServletRequest request) {
        return disabled;
    }
    
    @Override
    public boolean isVisible(HttpServletRequest request) {
        return visible;
    }
    
}
