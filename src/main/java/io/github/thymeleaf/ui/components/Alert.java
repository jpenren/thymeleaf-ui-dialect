package io.github.thymeleaf.ui.components;

import io.github.thymeleaf.ui.Component;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Alert extends Component {
    private final String message;
    private boolean dismissible;

    public static Alert with(String message) {
        return new Alert(message);
    }
    
    public static Alert dismissible(String message) {
        Alert alert = new Alert(message);
        alert.setDismissible(true);
        return alert;
    }

}
