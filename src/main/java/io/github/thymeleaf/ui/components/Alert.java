package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.Component;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Alert extends Component {
    private final String message;
    private boolean dismissible;

}
