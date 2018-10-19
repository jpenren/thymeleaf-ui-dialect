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
