package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Breadcrumb;

public class BreadcrumbAttributeTagProcessor extends AbstractTagProcessor {
    
    public BreadcrumbAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "breadcrumb");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Breadcrumb;
        Breadcrumb breadcrumb = isRenderable ? (Breadcrumb) target : new Breadcrumb();
        render(breadcrumb, context, tag, structureHandler, attributeName);
    }

}
