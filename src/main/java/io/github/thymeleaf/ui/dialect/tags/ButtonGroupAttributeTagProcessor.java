package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.ButtonGroup;

public class ButtonGroupAttributeTagProcessor extends AbstractTagProcessor {
    
    public ButtonGroupAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "button-group");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof ButtonGroup;
        ButtonGroup button = isRenderable ? (ButtonGroup) target : new ButtonGroup();
        render(button, context, tag, structureHandler, attributeName);
    }

}
