package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Figure;
import io.github.thymeleaf.ui.elements.Image;

public class FigureAttributeTagProcessor extends AbstractTagProcessor {
    
    public FigureAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "figure");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Figure;
        Figure figure = isRenderable ? (Figure) target : new Figure();

        boolean isImage = target instanceof Image;
        if(isImage) {
            figure.setImage((Image) target);
        }
        
        render(figure, context, tag, structureHandler, attributeName);
    }

}
