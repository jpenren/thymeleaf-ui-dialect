package io.github.thymeleaf.ui.components;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import lombok.Data;

public class Breadcrumb extends Component {
    private final List<Location> locations = new ArrayList<>();

    public Breadcrumb location(String text) {
        return location(new Location(Strings.EMPTY, text));
    }

    public Breadcrumb location(String href, String text) {
        return location(new Location(href, text));
    }

    public Breadcrumb location(Location entry) {
        locations.add(entry);
        return this;
    }
    
    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    @Data
    public static class Location {
        private final String href;
        private final String text;
        
        public boolean isLink() {
            return Strings.isNotEmpty(href);
        }
        
        public boolean isText() {
            return Strings.isEmpty(href);
        }
    }

}
