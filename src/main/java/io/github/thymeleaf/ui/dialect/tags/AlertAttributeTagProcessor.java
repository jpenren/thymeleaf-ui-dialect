package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Alert;
import io.github.thymeleaf.ui.internal.Strings;

public class AlertAttributeTagProcessor extends AbstractTagProcessor {
    
    public AlertAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "alert");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isAnAlert = target instanceof Alert;
        Alert alert = isAnAlert ? (Alert) target : new Alert(Strings.asString(target));
        render(alert, context, tag, structureHandler, attributeName);
    }

}
