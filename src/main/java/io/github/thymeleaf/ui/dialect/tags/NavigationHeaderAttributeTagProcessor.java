package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.IEngineContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.NavigationHeader;
import io.github.thymeleaf.ui.components.Sidebar;

public class NavigationHeaderAttributeTagProcessor extends AbstractTagProcessor {
    
    private static final String ATTRIBUTE_NAME = "nav-header";

    public NavigationHeaderAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, ATTRIBUTE_NAME);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        NavigationHeader nav = resolve(attributeValue, context);
        render(nav, context, tag, structureHandler, attributeName);
    }
    
    private NavigationHeader resolve(String attributeValue, ITemplateContext context) {
        if(attributeValue==null) {
            Object variable = context.getVariable("navigationHeader");
            return variable instanceof Sidebar ? (NavigationHeader) variable : new NavigationHeader();
        }
        
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof NavigationHeader;
        if(isRenderable) {
            return (NavigationHeader) target;
        }
        
        NavigationHeader navHeader = new NavigationHeader();
        if(target instanceof String) {
            ((IEngineContext)context).setVariable((String)target, navHeader);
        }
        
        return navHeader;
    }
    
}
