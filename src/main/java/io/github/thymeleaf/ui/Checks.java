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

package io.github.thymeleaf.ui;

public final class Checks {
    
    private Checks() {
    }
    
    public static void checkNotNullArgument(Object param) {
        if(param==null) {
            throw new IllegalArgumentException("Argument can not be null");
        }
    }
    
    public static void checkIsNotEmpty(String param) {
        if(param==null) {
            throw new IllegalArgumentException("Argument can not be null");
        }
        
        if(param.length()==0) {
            throw new IllegalArgumentException("Argument can not be empty");
        }
    }

}
