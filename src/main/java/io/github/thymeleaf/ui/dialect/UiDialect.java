package io.github.thymeleaf.ui.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public final class UiDialect extends AbstractProcessorDialect {

    private static final String PREFIX = "ui";

    public UiDialect() {
        super("User Interface Components Dialect", PREFIX, 1000);
    }

    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, PREFIX));
        processors.add(new RenderAttributeTagProcessor(dialectPrefix));
        
        return processors;
    }

}