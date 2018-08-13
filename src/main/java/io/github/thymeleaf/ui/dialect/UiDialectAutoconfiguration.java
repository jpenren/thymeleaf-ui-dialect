package io.github.thymeleaf.ui.dialect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class UiDialectAutoconfiguration {

    @Bean
    public UiDialect uiDialect() {
        return new UiDialect();
    }

    @Bean
    public ITemplateResolver uiTemplateResolver(@Value("${spring.thymeleaf.cache:true}") boolean cacheable,
            @Value("${thymeleaf.ui.theme:bs4}") String theme) {
        final UiTemplateResolver templateResolver = new UiTemplateResolver(theme);
        templateResolver.setCacheable(cacheable);

        return templateResolver;
    }

}
