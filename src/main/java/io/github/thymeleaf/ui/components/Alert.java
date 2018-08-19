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
    
    public static Alert with(String message) {
        return new Alert(message);
    }
    
    public static Alert dismissible(String message) {
        return new Alert(message).dismissible();
    }

}
