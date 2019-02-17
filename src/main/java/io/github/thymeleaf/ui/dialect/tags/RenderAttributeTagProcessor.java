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

package io.github.thymeleaf.ui.dialect.tags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.Renderable;

public final class RenderAttributeTagProcessor extends AbstractTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RenderAttributeTagProcessor.class);
    private static final String ATTR_NAME = "render";

    public RenderAttributeTagProcessor(final String dialectPrefix) {
        super(dialectPrefix, ATTR_NAME);
    }

    protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {
        
        final Object target = evaluate(attributeValue, context);
        if (target instanceof Renderable) {
            render((Renderable)target, context, tag, structureHandler);
        } else {
            LOGGER.debug("Target object '{}' is not Renderable", attributeValue);
        }
    }
    
}