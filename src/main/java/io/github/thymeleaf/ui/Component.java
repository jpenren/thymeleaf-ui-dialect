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

import io.github.thymeleaf.ui.internal.Strings;
import lombok.Getter;
import lombok.Setter;

/**
 * Any HTML element with template
 */
@Getter
@Setter
public abstract class Component extends Element implements Renderable {
    private String template = defaultTemplate();

    // Default template name: ClassName to dash => class-name
    private String defaultTemplate() {
        final String simpleName = getClass().getSimpleName();
        final boolean isAnonimous = Strings.isEmpty(simpleName);
        final String className = isAnonimous ? getClass().getSuperclass().getSimpleName() : simpleName;
        
        return "/components/" + Strings.dash(className);
    }

}
