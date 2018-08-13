package io.github.thymeleaf.ui.elements;

import io.github.thymeleaf.ui.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Image extends Element {
    private final String src;
    private String alt;

}
