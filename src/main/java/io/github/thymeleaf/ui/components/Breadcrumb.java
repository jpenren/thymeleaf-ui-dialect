package io.github.thymeleaf.ui.components;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.Strings;
import io.github.thymeleaf.ui.Urls;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Breadcrumb extends Component {
    private final @Getter List<Location> locations = new ArrayList<>();

    public Breadcrumb location(String text) {
        return location(new Location(Strings.EMPTY, text));
    }

    public Breadcrumb location(String href, String text) {
        return location(new Location(href, text));
    }

    public Breadcrumb location(Location location) {
        this.locations.add(location);
        return this;
    }
    
    public Breadcrumb locations(Location ... locations) {
        this.locations.addAll(Arrays.asList(locations));
        return this;
    }
    
//    public List<Location> getLocations() {
//        return Collections.unmodifiableList(locations);
//    }

    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Location {
        private final String href;
        private @Getter String text;
        
        public boolean hasLink() {
            return Strings.isNotEmpty(href);
        }
        
        public String getHref(HttpServletRequest request) {
            return "";//Urls.resolve(href, request);
        }
        
    }

}
