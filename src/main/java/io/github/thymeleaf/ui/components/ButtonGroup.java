/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.internal.Checks;
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
