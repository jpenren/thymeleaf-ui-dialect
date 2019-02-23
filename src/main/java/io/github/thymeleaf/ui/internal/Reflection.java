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
