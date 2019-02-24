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

import org.thymeleaf.context.IEngineContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Sidebar;

public class SidebarAttributeTagProcessor extends AbstractTagProcessor {
    
    private static final String ATTRIBUTE_NAME = "sidebar";

    public SidebarAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, ATTRIBUTE_NAME);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Sidebar sidebar = resolve(attributeValue, context);
        render(sidebar, context, tag, structureHandler, attributeName);
    }
    
    private Sidebar resolve(String attributeValue, ITemplateContext context) {
        if(attributeValue==null) {
            Object variable = context.getVariable(ATTRIBUTE_NAME);
            return variable instanceof Sidebar ? (Sidebar) variable : new Sidebar();
        }
        
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Sidebar;
        if(isRenderable) {
            return (Sidebar) target;
        }
        
        Sidebar sidebar = new Sidebar();
        if(target instanceof String) {
            ((IEngineContext)context).setVariable((String)target, sidebar);
        }
        
        return sidebar;
    }

}
