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
