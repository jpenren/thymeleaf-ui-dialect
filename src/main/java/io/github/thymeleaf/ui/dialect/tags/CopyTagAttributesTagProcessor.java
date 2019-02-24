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

import java.util.Collections;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.thymeleaf.ui.Attrs;
import io.github.thymeleaf.ui.HasHtmlAttributes;
import io.github.thymeleaf.ui.internal.Strings;

public class CopyTagAttributesTagProcessor extends AbstractAttributeTagProcessor {
    
    private static final int PRECEDENCE = Integer.MAX_VALUE;
    private static final String ATTR_NAME = "copy-tag-attributes";
    public static final String PARENT_ATTRIBUTES_VAR = "_tag_node_attributes";

    public CopyTagAttributesTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        
        IAttribute[] parentAttributes = getTagAttributes(context);
        for (IAttribute iAttribute : parentAttributes) {
            AttributeName attribute = iAttribute.getAttributeDefinition().getAttributeName();
            if(isStandardAttribute(attribute)) {
                String value = iAttribute.getValue();
                String name = iAttribute.getAttributeCompleteName();
                structureHandler.setAttribute(name, value);
            }
        }
        
        // Overrides id and class attributes from target object
        Object selectionTarget = context.getSelectionTarget();
        if(selectionTarget instanceof HasHtmlAttributes) {
            HasHtmlAttributes target = (HasHtmlAttributes) selectionTarget;
            if(Strings.isNotNull(target.getId())) {
                structureHandler.setAttribute(Attrs.ID, target.getId());
            }
            
            if(Strings.isNotNull(target.getClassName())) {
                structureHandler.setAttribute(Attrs.CLASS, target.getClassName());
            }
        }
    }

    private IAttribute[] getTagAttributes(ITemplateContext context) {
        Object variable = context.getVariable(PARENT_ATTRIBUTES_VAR);
        return (IAttribute[]) (variable==null ? Collections.emptyList() : variable);
    }
    
    private boolean isStandardAttribute(AttributeName attributeName) {
        return !getDialectPrefix().equals(attributeName.getPrefix());
    }
    
}
