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
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.Attrs;
import io.github.thymeleaf.ui.components.Carousel;

public class CarouselAttributeTagProcessor extends AbstractTagProcessor {
    
    public CarouselAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "carousel");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Carousel;
        Carousel carousel = isRenderable ? (Carousel) target : new Carousel();
        
        //Carousel needs an id for indicator links
        if(target instanceof String) {
            carousel.setId((String) target);
        }
        
        if(tag.hasAttribute(Attrs.ID)) {
            IAttribute attribute = tag.getAttribute(Attrs.ID);
            carousel.setId(attribute.getValue());
        }
        
        if(carousel.getId()==null) {
            carousel.setId("car_"+System.currentTimeMillis());
        }
        
        render(carousel, context, tag, structureHandler, attributeName);
    }

}
