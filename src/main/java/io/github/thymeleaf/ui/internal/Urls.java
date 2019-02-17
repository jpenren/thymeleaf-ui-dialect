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

package io.github.thymeleaf.ui.internal;

import javax.servlet.http.HttpServletRequest;

public final class Urls {
    // Match absolute uri (http://www.google.com | //www.google.com)
    private static final String ABSOLUTE_URI_REGEX = ".*//.*";

    private Urls() {
    }

    public static String resolve(String href, HttpServletRequest request) {
        if("#".equals(href)) {
            return href;
        }
        String contextPath = request==null ? Strings.EMPTY : request.getContextPath();
        
        return href.matches(ABSOLUTE_URI_REGEX) ? href :  contextPath + href;
    }

}
