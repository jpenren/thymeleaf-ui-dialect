package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ButtonGroup extends Component {
    private final List<Component> items = new ArrayList<>();
    private String label;
    
    public ButtonGroup buttons(Button...buttons) {
        if(buttons==null) {
            throw new IllegalArgumentException("Argument can not be null!");
        }
        
        this.items.addAll(Arrays.asList(buttons));
        return this;
    }
    
}
