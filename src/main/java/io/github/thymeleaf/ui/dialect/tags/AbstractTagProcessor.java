package io.github.thymeleaf.ui.dialect.tags;

import static io.github.thymeleaf.ui.dialect.tags.CopyTagAttributesTagProcessor.PARENT_ATTRIBUTES_VAR;
import static org.thymeleaf.standard.expression.StandardExpressions.getExpressionParser;

import java.io.StringWriter;
import java.util.Collections;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.IEngineContext;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.engine.TemplateManager;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.thymeleaf.ui.Renderable;
import io.github.thymeleaf.ui.internal.Reflection;
import io.github.thymeleaf.ui.internal.Strings;

abstract class AbstractTagProcessor extends AbstractAttributeTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTagProcessor.class);
    private static final Pattern CLEANER = Pattern.compile("(?m)^[ \t]*\r?\n");
    private static final int PRECEDENCE = 10000;

    protected AbstractTagProcessor(String dialectPrefix, String attributeName) {
        super(TemplateMode.HTML, dialectPrefix, null, false, attributeName, true, PRECEDENCE, true);
    }

    protected Object evaluate(String attributeValue, IExpressionContext context) {
        final String value = String.valueOf(attributeValue);
        final IStandardExpressionParser expressionParser = getExpressionParser(context.getConfiguration());
        final IStandardExpression expression = expressionParser.parseExpression(context, value);

        return expression.execute(context);
    }
    
    protected void render(Renderable renderable, ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler, AttributeName attributeName) {
        // IEngineContext should not be used but
        // structureHandler.setSelectionTarget(target); do not process variable until
        // the next execution
        if (!(context instanceof IEngineContext)) {
            throw new IllegalStateException("IEngineContext expected at this point!");
        }
        
        // Available data on template
        final IEngineContext engineContext = (IEngineContext) context;
        engineContext.setVariable(PARENT_ATTRIBUTES_VAR, tag.getAllAttributes());
        final Object previousSelectionTarget = engineContext.getSelectionTarget();
        assignProperties(renderable, tag, engineContext, attributeName);
        engineContext.setSelectionTarget(renderable);

        // Process template
        final String template = renderable.getTemplate();
        final TemplateManager templateManager = context.getConfiguration().getTemplateManager();
        final TemplateSpec templateSpec = new TemplateSpec(template, Collections.<String, Object>emptyMap());
        final StringWriter writer = new StringWriter();
        templateManager.parseAndProcess(templateSpec, context, writer);
        final String content = writer.toString();
        final String adjusted = CLEANER.matcher(content).replaceAll(Strings.EMPTY);
        
        structureHandler.replaceWith(adjusted, false);
        engineContext.removeVariable(PARENT_ATTRIBUTES_VAR);
        engineContext.setSelectionTarget(previousSelectionTarget);
    }
    
    protected void assignProperties(Renderable renderable, IProcessableElementTag tag, ITemplateContext context, AttributeName skipAttribute) {
        final IAttribute[] allAttributes = tag.getAllAttributes();
        for (IAttribute attribute : allAttributes) {
            final AttributeName attr = attribute.getAttributeDefinition().getAttributeName();
            final boolean skip = attr.equals(skipAttribute);
            if(getDialectPrefix().equals(attr.getPrefix()) && !skip) {
                final Object value = evaluate(attribute.getValue(), context);
                try {
                    Reflection.set(renderable, attr.getAttributeName(), value);
                } catch (Exception e) {
                    LOGGER.error("Unable to set value", e);
                }
            }
        }
    }
    
}
