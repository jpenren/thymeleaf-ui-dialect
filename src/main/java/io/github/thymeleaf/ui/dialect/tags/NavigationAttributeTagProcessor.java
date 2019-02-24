package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Navigation;

public class NavigationAttributeTagProcessor extends AbstractTagProcessor {
    
    public NavigationAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "nav");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Navigation;
        Navigation nav = isRenderable ? (Navigation) target : new Navigation();
        render(nav, context, tag, structureHandler, attributeName);
    }

}
