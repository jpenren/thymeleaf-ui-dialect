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
    
    public void add(Button...buttons) {
        Checks.checkNotNullArgument(buttons);
        for (Button button : buttons) {
            this.buttons.add(button);
        }
    }
    
    public List<Button> getButtons(){
        return Collections.unmodifiableList(buttons);
    }
    
}
