package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ButtonGroup extends Component {
    private final List<Button> buttons = new ArrayList<>();
    private String label;
    
    public void add(Button...buttons) {
        Checks.checkNotNullArgument(buttons);
        for (Button button : buttons) {
            this.buttons.add(button);
        }
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
        return with(Strings.EMPTY, buttons);
    }
    
    public static ButtonGroup with(String label, Button ... buttons) {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.setLabel(label);
        buttonGroup.add(buttons);
        return buttonGroup;
    }
    
}
