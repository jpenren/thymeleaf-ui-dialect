package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.Component;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Alert extends Component {
    private final String message;
    private boolean dismissible;

    public Alert dismissible() {
        this.dismissible = true;
        return this;
    }

}
