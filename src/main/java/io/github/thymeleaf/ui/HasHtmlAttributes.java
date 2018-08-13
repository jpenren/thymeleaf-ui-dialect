package io.github.thymeleaf.ui;

/**
 * Elements that can handle id attribute
 */
public interface HasHtmlAttributes {

    String getId();

    void setId(String id);
    
    String getClassName();
    
    void setClassName(String className);
    
}
