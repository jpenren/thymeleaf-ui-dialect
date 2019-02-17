package io.github.thymeleaf.ui.dialect.tags;

import static io.github.thymeleaf.ui.dialect.tags.CopyParentAttributesTagProcessor.PARENT_ATTRIBUTES_VAR;
import static org.thymeleaf.standard.expression.StandardExpressions.getExpressionParser;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;

import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.IEngineContext;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.TemplateManager;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.thymeleaf.ui.Renderable;

abstract class AbstractTagProcessor extends AbstractAttributeTagProcessor {

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
    
    protected void render(Renderable renderable, ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
        // IEngineContext should not be used but
        // structureHandler.setSelectionTarget(target); do not process variable until
        // the next execution
        if (!(context instanceof IEngineContext)) {
            throw new IllegalStateException("IEngineContext expected at this point!");
        }
        
        // Available data on template
        final IEngineContext engineContext = (IEngineContext) context;
        setParentAttributesAsLocalVariable(tag, engineContext);
        Object previousSelectionTarget = engineContext.getSelectionTarget();
        engineContext.setSelectionTarget(renderable);

        // Process template
        final String template = renderable.getTemplate();
        final TemplateManager templateManager = context.getConfiguration().getTemplateManager();
        final TemplateSpec templateSpec = new TemplateSpec(template, Collections.<String, Object>emptyMap());
        final StringWriter writer = new StringWriter();
        templateManager.parseAndProcess(templateSpec, context, writer);
        final String content = writer.toString();
        String adjusted = CLEANER.matcher(content).replaceAll("");
        
        structureHandler.replaceWith(adjusted, false);
        engineContext.removeVariable(PARENT_ATTRIBUTES_VAR);
        engineContext.setSelectionTarget(previousSelectionTarget);
    }
    
    private void setParentAttributesAsLocalVariable(IProcessableElementTag tag, IEngineContext engineContext) {
        Collection<IAttribute> parentAttributes = Arrays.asList(tag.getAllAttributes());
        engineContext.setVariable(PARENT_ATTRIBUTES_VAR, parentAttributes);
    }
    
}
