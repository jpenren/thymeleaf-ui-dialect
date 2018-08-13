package io.github.thymeleaf.ui.dialect;

import static io.github.thymeleaf.ui.Strings.isNotEmpty;
import static org.thymeleaf.standard.expression.StandardExpressions.getExpressionParser;

import java.io.StringWriter;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.IEngineContext;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.engine.TemplateManager;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.thymeleaf.ui.HasHtmlAttributes;
import io.github.thymeleaf.ui.Renderable;

final class RenderAttributeTagProcessor extends AbstractAttributeTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RenderAttributeTagProcessor.class);
    private static final String ATTR_NAME = "render";
    private static final int PRECEDENCE = 10000;

    public RenderAttributeTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
    }

    protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

        // IEngineContext should not be used but
        // structureHandler.setSelectionTarget(target); do not process variable until
        // the next execution
        if (!(context instanceof IEngineContext)) {
            throw new IllegalStateException("IEngineContext expected at this point!");
        }
        
        final IEngineContext engineContext = (IEngineContext) context;
        final Object target = evaluate(attributeValue, context);
        if (target instanceof Renderable) {
            copyAttributes(tag, target);
            engineContext.setSelectionTarget(target);
            final String template = ((Renderable) target).getTemplate();
            final String content = render(template, context);
            structureHandler.replaceWith(content, true);
        } else {
            LOGGER.debug("Target object '{}' is not Renderable", attributeValue);
        }
    }
    
    private Object evaluate(String expressionValue, IExpressionContext context) {
        final String value = String.valueOf(expressionValue).trim();
        final IStandardExpressionParser expressionParser = getExpressionParser(context.getConfiguration());
        final IStandardExpression expression = expressionParser.parseExpression(context, value);

        return expression.execute(context);
    }

    private String render(String template, final ITemplateContext context) {
        final TemplateManager templateManager = context.getConfiguration().getTemplateManager();
        final TemplateSpec templateSpec = new TemplateSpec(template, Collections.<String, Object>emptyMap());
        final StringWriter writer = new StringWriter();
        templateManager.parseAndProcess(templateSpec, context, writer);

        return writer.toString();
    }
    
    /**
     * Copy attributes from original tag
     * @param tag where ui:render is attached
     * @param target object to be rendered
     */
    private void copyAttributes(IProcessableElementTag tag, Object target) {
        if(target instanceof HasHtmlAttributes) {
            HasHtmlAttributes htmlObject = (HasHtmlAttributes) target;
            final String sourceId = tag.getAttributeValue("id");
            final String className = tag.getAttributeValue("class");
            if(isNotEmpty(sourceId)) {
                htmlObject.setId(sourceId);
            }
            if(isNotEmpty(className)) {
                htmlObject.setClassName(className);
            }
        }
    }

}