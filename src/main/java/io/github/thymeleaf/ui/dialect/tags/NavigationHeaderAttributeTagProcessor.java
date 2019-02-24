package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.NavigationHeader;

public class NavigationHeaderAttributeTagProcessor extends AbstractTagProcessor {
    
    private static final String ATTRIBUTE_NAME = "nav-header";

    public NavigationHeaderAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, ATTRIBUTE_NAME);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof NavigationHeader;
        NavigationHeader nav = isRenderable ? (NavigationHeader) target : new NavigationHeader();
        render(nav, context, tag, structureHandler, attributeName);
    }
    
}
