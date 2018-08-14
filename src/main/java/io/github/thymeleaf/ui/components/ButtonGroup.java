package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ButtonGroup extends Component {
    private final List<Button> buttons = new ArrayList<>();
    private @Getter String label;
    
    public ButtonGroup buttons(Button...buttons) {
        Checks.checkNotNullArgument(buttons);
        this.buttons.addAll(Arrays.asList(buttons));
        return this;
    }
    
    public List<Button> getButtons(){
        return Collections.unmodifiableList(buttons);
    }
    
}
