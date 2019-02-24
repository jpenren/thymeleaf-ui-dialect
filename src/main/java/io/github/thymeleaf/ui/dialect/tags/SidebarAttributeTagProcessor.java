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
