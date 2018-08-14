package io.github.thymeleaf.ui;

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
    
    public static final String dash(String text) {
        return text==null ? EMPTY : text.replaceAll("([a-z])([A-Z]+)", "$1-$2").toLowerCase();
    }

}
