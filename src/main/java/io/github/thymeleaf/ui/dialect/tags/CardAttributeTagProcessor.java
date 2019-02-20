package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Card;

public class CardAttributeTagProcessor extends AbstractTagProcessor {
    
    public CardAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "card");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Card;
        Card card = isRenderable ? (Card) target : new Card();
        render(card, context, tag, structureHandler, attributeName);
    }

}
