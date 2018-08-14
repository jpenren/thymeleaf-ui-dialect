package io.github.thymeleaf.ui;

public final class Checks {
    
    private Checks() {
    }
    
    public static void checkNotNullArgument(Object param) {
        if(param==null) {
            throw new IllegalArgumentException("Argument can not be null");
        }
    }

}
