package io.github.thymeleaf.ui.dialect;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static io.github.thymeleaf.ui.Components.*;

public class DialectBenchmarkTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DialectBenchmarkTest.class);
    
    @Test
    @Ignore
    public void benchmark() throws Exception {
        ClassLoaderTemplateResolver res = new ClassLoaderTemplateResolver();
        res.setCacheable(false);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addTemplateResolver(res);

        UiDialect uiDialect = new UiDialect();
        engine.addDialect(uiDialect);
        ComponentTemplateResolver uiTemplateResolver = new ComponentTemplateResolver("default");
        uiTemplateResolver.setOrder(1);
        uiTemplateResolver.setCacheable(false);
        engine.addTemplateResolver(uiTemplateResolver);

        long startup = System.currentTimeMillis();

        String html = null;
        for (int i = 0; i < 10000; i++) {
            Context context = new Context();
            context.setVariable("component", carousel(slide("src", "caption text"), slide("src", "caption text")));
            html = engine.process("/test.html", context);
        }

        LOGGER.info(html);

        LOGGER.info("time: " + (System.currentTimeMillis() - startup) + "ms");
    }
}
