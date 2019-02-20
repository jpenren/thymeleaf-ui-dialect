package io.github.thymeleaf.ui.dialect.tags;

import java.util.Collections;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.thymeleaf.ui.Attrs;
import io.github.thymeleaf.ui.HasHtmlAttributes;
import io.github.thymeleaf.ui.internal.Strings;

public class CopyTagAttributesTagProcessor extends AbstractAttributeTagProcessor {
    
    private static final int PRECEDENCE = Integer.MAX_VALUE;
    private static final String ATTR_NAME = "copy-tag-attributes";
    public static final String PARENT_ATTRIBUTES_VAR = "_tag_node_attributes";

    public CopyTagAttributesTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, IElementTagStructureHandler structureHandler) {
        
        IAttribute[] parentAttributes = getParentAttributes(context);
        for (IAttribute iAttribute : parentAttributes) {
            AttributeName attribute = iAttribute.getAttributeDefinition().getAttributeName();
            if(isStandardAttribute(attribute)) {
                String value = iAttribute.getValue();
                String name = iAttribute.getAttributeCompleteName();
                structureHandler.setAttribute(name, value);
            }
        }
        
        // Overrides id and class attributes from target object
        Object selectionTarget = context.getSelectionTarget();
        if(selectionTarget instanceof HasHtmlAttributes) {
            HasHtmlAttributes target = (HasHtmlAttributes) selectionTarget;
            if(Strings.isNotNull(target.getId())) {
                structureHandler.setAttribute(Attrs.ID, target.getId());
            }
            
            if(Strings.isNotNull(target.getClassName())) {
                structureHandler.setAttribute(Attrs.CLASS, target.getClassName());
            }
        }
    }

    private IAttribute[] getParentAttributes(ITemplateContext context) {
        Object variable = context.getVariable(PARENT_ATTRIBUTES_VAR);
        return (IAttribute[]) (variable==null ? Collections.emptyList() : variable);
    }
    
    private boolean isStandardAttribute(AttributeName attributeName) {
        return attributeName.getPrefix()==null;
    }
    
}
