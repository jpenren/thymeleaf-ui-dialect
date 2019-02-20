package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Badge;
import io.github.thymeleaf.ui.internal.Strings;

public class BadgeAttributeTagProcessor extends AbstractTagProcessor {
    
    public BadgeAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "badge");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isRenderable = target instanceof Badge;
        Badge badge = isRenderable ? (Badge) target : new Badge(Strings.asString(target));
        render(badge, context, tag, structureHandler, attributeName);
    }

}
