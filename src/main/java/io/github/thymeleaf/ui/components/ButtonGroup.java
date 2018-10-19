package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ButtonGroup extends Component {
    private final List<Button> buttons = new ArrayList<>();
    private String label;
    
    public ButtonGroup add(Button button) {
        this.buttons.add(button);
        return this;
    }
    
    public ButtonGroup add(Button...buttons) {
        Checks.checkNotNullArgument(buttons);
        for (Button button : buttons) {
            this.buttons.add(button);
        }
        return this;
    }
    
    public List<Button> getButtons(){
        return Collections.unmodifiableList(buttons);
    }
    
    public static ButtonGroup empty() {
        return new ButtonGroup();
    }
    
    public static ButtonGroup with(String label) {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.setLabel(label);
        return buttonGroup;
    }
    
    public static ButtonGroup with(Button ... buttons) {
        return empty().add(buttons);
    }
    
    public static ButtonGroup with(String label, Button ... buttons) {
        return with(label).add(buttons);
    }
    
}
