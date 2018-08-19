package io.github.thymeleaf.ui.components;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Checks;
import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Element;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.Urls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Breadcrumb extends Component {
    private final List<Location> locations = new ArrayList<>();

    public Breadcrumb addLocation(String text) {
        return add(Location.with(text));
    }

    public Breadcrumb addLocation(String href, String text) {
        return add(Location.with(href, text));
    }

    public Breadcrumb add(Location location) {
        this.locations.add(location);
        return this;
    }
    
    public Breadcrumb add(Location ... locations) {
        Checks.checkNotNullArgument(locations);
        for (Location location : locations) {
            this.locations.add(location);
        }
        return this;
    }
    
    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    @RequiredArgsConstructor
    public static class Location extends Element {
        private final String href;
        private final @Getter String text;
        
        public boolean hasLink() {
            return Strings.isNotEmpty(href);
        }
        
        public String getHref(HttpServletRequest request) {
            return Urls.resolve(href, request);
        }
        
        public static Location with(String href, String text) {
            return new Location(href, text);
        }
        
        public static Location with(String text) {
            return with(Strings.EMPTY, text);
        }
        
    }
    
    public static Breadcrumb empty() {
        return new Breadcrumb();
    }
    
    public static Breadcrumb with(Location ... locations) {
        return new Breadcrumb().add(locations);
    }

}
