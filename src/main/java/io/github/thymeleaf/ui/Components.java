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

package io.github.thymeleaf.ui;

import io.github.thymeleaf.ui.components.Alert;
import io.github.thymeleaf.ui.components.Badge;
import io.github.thymeleaf.ui.components.Breadcrumb;
import io.github.thymeleaf.ui.components.Button;
import io.github.thymeleaf.ui.components.ButtonGroup;
import io.github.thymeleaf.ui.components.Card;
import io.github.thymeleaf.ui.components.Carousel;
import io.github.thymeleaf.ui.components.Dropdown;
import io.github.thymeleaf.ui.components.Figure;
import io.github.thymeleaf.ui.components.Navigation;
import io.github.thymeleaf.ui.components.NavigationHeader;
import io.github.thymeleaf.ui.components.Sidebar;
import io.github.thymeleaf.ui.components.Breadcrumb.Location;
import io.github.thymeleaf.ui.components.Carousel.Slide;
import io.github.thymeleaf.ui.components.Dropdown.Toggle;
import io.github.thymeleaf.ui.components.Sidebar.Submenu;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.elements.Link;
import io.github.thymeleaf.ui.internal.Strings;

/**
 * A set of methods useful for create components. These methods can be used directly:
 * <code>Components.alert(...)</code>, however, they read better if they
 * are referenced through static import:
 *
 * <pre>
 * import static io.github.thymeleaf.ui.Components.*;
 *    ...
 *    alert(...);
 * </pre>
 * 
 * @author jpenren
 *
 */
public final class Components {

    private Components() {
        // Utility
    }
    
    public static Alert alert(String message) {
        return new Alert(message);
    }
    
    public static Alert alertDismissible(String message) {
        Alert alert = new Alert(message);
        alert.setDismissible(true);
        return alert;
    }
    
    public static Badge badge(String text) {
        return new Badge(text);
    }
    
    public static Badge badge(String href, String text) {
        Badge badge = new Badge(text);
        badge.setHref(href);
        return badge;
    }
    
    public static Breadcrumb breadcrumb() {
        return new Breadcrumb();
    }

    public static Breadcrumb breadcrumb(Location... locations) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.add(locations);
        return breadcrumb;
    }
    
    public static Location location(String text) {
        return new Location(text);
    }
    
    public static Location location(String href, String text) {
        return new Location(href, text);
    }
    
    public static Button button(String text) {
        return new Button(text);
    }
    
    public static Button button(String text, String type) {
        Button button = new Button(text);
        button.setType(type);
        return button;
    }
    
    public static Button buttonToggle(String text) {
        Button button = new Button(text);
        button.setToggle(true);
        return button;
    }
    
    public static Button buttonLink(String href, String text) {
        Button button = new Button(text);
        button.setHref(href);
        return button;
    }
    
    public static ButtonGroup buttonGroup() {
        return new ButtonGroup();
    }
    
    public static ButtonGroup buttonGroup(String label) {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.setLabel(label);
        return buttonGroup;
    }
    
    public static ButtonGroup buttonGroup(Button ... buttons) {
        return buttonGroup(Strings.EMPTY, buttons);
    }
    
    public static ButtonGroup buttonGroup(String label, Button ... buttons) {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.setLabel(label);
        buttonGroup.add(buttons);
        return buttonGroup;
    }
    
    public static Card card() {
        return new Card();
    }
    
    public static Card card(String src) {
        return card(src, null);
    }
    
    public static Card card(String src, String alt) {
        return new Card(image(src, alt));
    }
    
    public static Carousel carousel() {
        return new Carousel();
    }
    
    public static Carousel carousel(String id) {
        Carousel carousel = new Carousel();
        carousel.setId(id);
        return carousel;
    }
    
    public static Carousel carousel(Slide ... slides) {
        return carousel(null, slides);
    }
    
    public static Carousel carousel(String id, Slide ... slides) {
        Carousel carousel = new Carousel();
        carousel.setId(id);
        for (Slide slide : slides) {
            carousel.add(slide);
        }
        
        return carousel;
    }
    
    public static Slide slide(String src) {
        return new Slide(new Image(src));
    }
    
    public static Slide slide(String src, String caption) {
        Slide slide = new Slide(new Image(src));
        slide.setCaption(caption);
        return slide;
    }
    
    public static Dropdown dropdown(String text) {
        return dropdown(text, null);
    }
    
    public static Dropdown dropdown(String text, String toggleId) {
        Toggle toggle = new Toggle(text);
        toggle.setId(toggleId);
        return new Dropdown(toggle);
    }
    
    public static Figure figure(Image image) {
        return new Figure(image);
    }
    
    public static Figure figure(String src) {
        return new Figure(image(src));
    }
    
    public static Figure figure(String src, String alt) {
        return new Figure(image(src, alt));
    }
    
    public static Figure figure(String src, String alt, String title) {
        return new Figure(image(src, alt, title));
    }
    
    public static Navigation navigation() {
        return new Navigation();
    }
    
    public static Navigation navigation(Link...links) {
        Navigation navigation = new Navigation();
        navigation.add(links);
        return navigation;
    }
    
    public static NavigationHeader navigationHeader() {
        return new NavigationHeader();
    }
    
    public static NavigationHeader navigationHeader(String href, String text) {
        return navigationHeader(href, text, new Link[0]);
    }
    
    public static NavigationHeader navigationHeader(Link... links) {
        NavigationHeader header = new NavigationHeader();
        header.add(links);
        return header;
    }
    
    public static NavigationHeader navigationHeader(String href, String text, Link...links) {
        NavigationHeader navigationHeader = new NavigationHeader();
        navigationHeader.setBrand(link(href, text));
        if( links!=null ) {
            navigationHeader.add(links);
        }
        
        return navigationHeader;
    }
    
    public static Sidebar sidebar() {
        return new Sidebar();
    }

    public static Sidebar sidebar(String header) {
        return sidebar(header, new Link[0]);
    }

    public static Sidebar sidebar(Link... links) {
        return sidebar(null, links);
    }

    public static Sidebar sidebar(String header, Link... links) {
        Sidebar sidebar = sidebar();
        sidebar.setHeader(header);
        if(links!=null) {
            sidebar.add(links);
        }
        
        return sidebar;
    }
    
    public static Submenu submenu(String text, Link... links) {
        return submenu(text, "menu_"+System.currentTimeMillis(), null, links);
    }
    
    public static Submenu submenu(String text, String id, Link... links) {
        return submenu(text, id, null, links);
    }
    
    public static Submenu submenu(String text, String id, String className, Link... links) {
        Submenu submenu = new Submenu(text, id, links);
        submenu.setClassName(className);
        return submenu;
    }
    
    public static Link link(String href, String text) {
        return new Link(href, text);
    }

    public static Link link(String href, String text, String title) {
        Link link = new Link(href, text);
        link.setTitle(title);
        return link;
    }

    public static Link link(String href, String text, String title, String target) {
        return link(href, text, title, target, null);
    }

    public static Link link(String href, String text, String title, String target, String rel) {
        Link link = new Link(href, text);
        link.setTitle(title);
        link.setTarget(target);
        link.setRel(rel);
        return link;
    }
    
    public static Image image(String src) {
        return new Image(src);
    }
    
    public static Image image(String src, String alt) {
        return new Image(src, alt, null);
    }
    
    public static Image image(String src, String alt, String title) {
        return new Image(src, alt, title);
    }
    
}
