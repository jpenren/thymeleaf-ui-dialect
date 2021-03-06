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

package io.github.thymeleaf.ui.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.thymeleaf.ui.dialect.tags.AlertAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.BadgeAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.BreadcrumbAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.ButtonAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.ButtonGroupAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.CardAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.CarouselAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.CopyTagAttributesTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.DropdownAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.FigureAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.NavigationAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.NavigationHeaderAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.RenderAttributeTagProcessor;
import io.github.thymeleaf.ui.dialect.tags.SidebarAttributeTagProcessor;

public final class UiDialect extends AbstractProcessorDialect implements IExpressionObjectDialect {

    private static final UiExpressionObjectFactory UI_FACTORY = new UiExpressionObjectFactory();
    private static final String PREFIX = "ui";

    public UiDialect() {
        super("User Interface Components Dialect", PREFIX, 1000);
    }

    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, PREFIX));
        processors.add(new CopyTagAttributesTagProcessor(dialectPrefix));
        processors.add(new RenderAttributeTagProcessor(dialectPrefix));
        processors.add(new AlertAttributeTagProcessor(dialectPrefix));
        processors.add(new BadgeAttributeTagProcessor(dialectPrefix));
        processors.add(new BreadcrumbAttributeTagProcessor(dialectPrefix));
        processors.add(new ButtonAttributeTagProcessor(dialectPrefix));
        processors.add(new ButtonGroupAttributeTagProcessor(dialectPrefix));
        processors.add(new CardAttributeTagProcessor(dialectPrefix));
        processors.add(new CarouselAttributeTagProcessor(dialectPrefix));
        processors.add(new DropdownAttributeTagProcessor(dialectPrefix));
        processors.add(new FigureAttributeTagProcessor(dialectPrefix));
        processors.add(new NavigationAttributeTagProcessor(dialectPrefix));
        processors.add(new NavigationHeaderAttributeTagProcessor(dialectPrefix));
        processors.add(new SidebarAttributeTagProcessor(dialectPrefix));
        
        return processors;
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return UI_FACTORY;
    }
    
}