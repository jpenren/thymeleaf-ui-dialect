package io.github.thymeleaf.ui;

public interface Condition<T> {
    
    boolean test(T request);
}
