/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.thymeleaf.ui.dialect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class UiDialectAutoconfiguration {

    @Value("${thymeleaf.ui.theme:}")
    private String theme;
    @Value("${spring.thymeleaf.cache:true}")
    private boolean cacheable;

    @Bean
    public UiDialect uiDialect() {
        return new UiDialect();
    }

    @Bean
    @Conditional(IsThemeConfigured.class)
    public ITemplateResolver componentTemplateResolver() {
        final ComponentTemplateResolver templateResolver = new ComponentTemplateResolver(theme);
        templateResolver.setOrder(Integer.MAX_VALUE-1); // allow override templates from default theme
        templateResolver.setCacheable(cacheable);
        templateResolver.setCheckExistence(true);

        return templateResolver;
    }

    @Bean
    public ITemplateResolver fallbackTemplateResolver() {
        final ComponentTemplateResolver templateResolver = new ComponentTemplateResolver("default");
        templateResolver.setOrder(Integer.MAX_VALUE);
        templateResolver.setCacheable(cacheable);
        templateResolver.setCheckExistence(true);

        return templateResolver;
    }
    
}
