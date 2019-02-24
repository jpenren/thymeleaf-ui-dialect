package io.github.thymeleaf.ui.dialect.tags;

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
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Sidebar;
        Sidebar sidebar = isRenderable ? (Sidebar) target : new Sidebar();
        render(sidebar, context, tag, structureHandler, attributeName);
    }

}
