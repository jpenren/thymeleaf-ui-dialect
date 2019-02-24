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

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.internal.Checks;
import io.github.thymeleaf.ui.internal.Strings;
import io.github.thymeleaf.ui.internal.Urls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Breadcrumb extends Component {
    private final List<Location> locations = new ArrayList<>();
    
    public void add(Location... locations) {
        Checks.checkNotNullArgument(locations);
        for (Location location : locations) {
            this.locations.add(location);
        }
    }
    
    public void setLocations(List<Location> locations) {
        this.locations.addAll(locations);
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    @RequiredArgsConstructor
    public static class Location extends Element {
        private final String href;
        private final @Getter String text;
        
        public Location(String text) {
            this.href = Strings.EMPTY;
            this.text = text;
        }

        public boolean hasLink() {
            return Strings.isNotEmpty(href);
        }

        public String getHref(HttpServletRequest request) {
            return Urls.resolve(href, request);
        }
        
    }

}
