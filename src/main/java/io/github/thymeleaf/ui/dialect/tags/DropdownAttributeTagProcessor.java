package io.github.thymeleaf.ui.dialect.tags;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import io.github.thymeleaf.ui.components.Dropdown;
import io.github.thymeleaf.ui.components.Dropdown.Toggle;
import io.github.thymeleaf.ui.internal.Strings;

public class DropdownAttributeTagProcessor extends AbstractTagProcessor {
    
    public DropdownAttributeTagProcessor(String dialectPrefix) {
        super(dialectPrefix, "dropdown");
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        Object target = evaluate(attributeValue, context);
        boolean isToggle = target instanceof Toggle;
        Toggle toggle = isToggle ? (Toggle)target : Toggle.toggle(Strings.asString(target));
        Dropdown dropdown = new Dropdown(toggle);
        render(dropdown, context, tag, structureHandler, attributeName);
    }

}
