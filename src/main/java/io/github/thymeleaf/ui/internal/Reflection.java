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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class Reflection {

    private Reflection() {
        // Utility
    }
    
    public static void set(Object obj, String propertyName, Object value) {
        if(value!=null) {
            final Method method = getMethod(obj, propertyName, value);
            try {
                method.invoke(obj, value);
            } catch (Exception e) {
                throw new IllegalStateException("Unable to set value for property "+propertyName, e);
            }
        }
    }
    
    private static Method getMethod(Object obj, String propertyName, Object value) {
        final String methodName = "set" + Strings.capitalize(propertyName);
        try {
            // try as primitive
            Class<?> t  = resolvePrimitive(value);
            return obj.getClass().getMethod(methodName, t);
        } catch (Exception e) {
            try {
                return obj.getClass().getMethod(methodName, value.getClass());
            } catch (Exception e1) {
                String m = obj.getClass().getName() +"."+ methodName;
                String arg = value==null ? "null" : String.valueOf(value.getClass());
                throw new IllegalStateException("Unable to found method: '"+m+"("+arg+")'", e1);
            }
        }
    }
    
    private static Class<?> resolvePrimitive(Object value) {
        if(value instanceof ArrayList) {
            return List.class;
        }
        
        if(value instanceof Boolean) {
            return Boolean.TYPE;
        }
        
        if(value instanceof Integer) {
            return Integer.TYPE;
        }
        
        throw new IllegalStateException("No primitive alternative");
    }

}
