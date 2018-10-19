package io.github.thymeleaf.ui;

public final class Conditions {
    
    private Conditions() {
    }
    
    public static <T> Condition<T> value(final boolean value){
        return (Condition<T>) new Condition<Object>() {

            @Override
            public boolean test(Object request) {
                return value;
            }
        };
    }
    
}
