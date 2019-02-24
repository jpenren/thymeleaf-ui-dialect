/*
 * Copyright 2019 the original author or authors.
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

package io.github.thymeleaf.ui.elements;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.internal.Checks;
import io.github.thymeleaf.ui.internal.Urls;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link extends Element {
    private final @Getter(value = AccessLevel.NONE) String href;
    private final String text;
    private String title;
    private String target;
    private String rel;
    
    public Link(String href, String text) {
        Checks.checkIsNotEmpty(href);
        Checks.checkIsNotEmpty(text);
        this.href = href;
        this.text = text;
    }
    
    public String getHref(HttpServletRequest request) {
        return Urls.resolve(href, request);
    }

}
