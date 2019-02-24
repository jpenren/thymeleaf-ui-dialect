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

package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Dropdown;
import io.github.thymeleaf.ui.components.Dropdown.Toggle;
import io.github.thymeleaf.ui.internal.Strings;

public class DropdownAttributeTagProcessor extends AbstractTagProcessor {
    
    public DropdownAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "dropdown");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isToggle = target instanceof Toggle;
        Toggle toggle = isToggle ? (Toggle)target : Toggle.toggle(Strings.asString(target));
        Dropdown dropdown = new Dropdown(toggle);
        render(dropdown, context, tag, structureHandler, attributeName);
    }

}
