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

public final class Strings {

    public static final String EMPTY = "";

    private Strings() {
    }

    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }

    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }
    
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static String dash(String str) {
        return str == null ? EMPTY : str.replaceAll("([a-z])([A-Z]+)", "$1-$2").toLowerCase();
    }
    
    public static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        
        final StringBuilder sb = new StringBuilder(str.length());
        final String[] strings = str.split("-");
        for (String string : strings) {
            sb.append(Character.toUpperCase(string.charAt(0)));
            sb.append(string.substring(1));
        }
        
        return sb.toString();
    }

    public static String asString(Object obj) {
        return obj instanceof String ? (String) obj : EMPTY;
    }
    
}
