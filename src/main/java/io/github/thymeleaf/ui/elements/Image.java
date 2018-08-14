package io.github.thymeleaf.ui.elements;

import io.github.thymeleaf.ui.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Image extends Element {
    private final String src;
    private String alt;

}
