package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Button;

public class ButtonAttributeTagProcessor extends AbstractTagProcessor {
    
    public ButtonAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "button");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Button;
        Button button = isRenderable ? (Button) target : new Button();
        render(button, context, tag, structureHandler, attributeName);
    }

}
