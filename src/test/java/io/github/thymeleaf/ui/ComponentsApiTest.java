package io.github.thymeleaf.ui;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.github.thymeleaf.ui.components.Alert;
import io.github.thymeleaf.ui.components.Badge;
import io.github.thymeleaf.ui.components.Breadcrumb;
import io.github.thymeleaf.ui.components.Breadcrumb.Location;
import io.github.thymeleaf.ui.components.Button;
import io.github.thymeleaf.ui.components.ButtonGroup;
import io.github.thymeleaf.ui.components.Card;
import io.github.thymeleaf.ui.components.Carousel;
import io.github.thymeleaf.ui.components.Carousel.Slide;
import io.github.thymeleaf.ui.components.Dropdown;
import io.github.thymeleaf.ui.components.Dropdown.Header;
import io.github.thymeleaf.ui.components.Dropdown.Toggle;
import io.github.thymeleaf.ui.components.Figure;
import io.github.thymeleaf.ui.components.Navigation;
import io.github.thymeleaf.ui.components.NavigationHeader;
import io.github.thymeleaf.ui.components.Sidebar;
import io.github.thymeleaf.ui.dialect.UiDialect;
import io.github.thymeleaf.ui.dialect.UiTemplateResolver;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.elements.Link;

public class ComponentsApiTest {
    
    @Test
    public void componentsApiTest() throws Exception {
        new Alert("text");
        new Alert("Alert text").setDismissible(true);
        Alert.with("text");
        Alert.dismissible("message");
        
        new Badge("3");
        Badge.with("3");
        Badge.with("#", "text");
        
        new Breadcrumb();
//        new Breadcrumb().addLocation("#", "text").addLocation("text");
        Breadcrumb.empty();
//        Breadcrumb.empty().addLocation("#", "text").addLocation("text");
        Breadcrumb.with(Location.with("#", "text"), Location.with("text"));
        
        new Button("text");
        Button.with("text");
        Button.with("text", "type");
        Button.toggle("text");
        Button.link("#", "text");
        
        new ButtonGroup();
        new ButtonGroup().add(Button.with("text"));
        new ButtonGroup().add(Button.with("text"), Button.with("text"));
        ButtonGroup.empty();
        ButtonGroup.empty().add(Button.with("text"));
        ButtonGroup.empty().add(Button.with("text"), Button.with("text"));
        ButtonGroup.with(Button.with("text"));
        ButtonGroup.with(Button.with("text"), Button.with("text"));
        
        new Card(new Image("src"));
        Card.with("src");
        Card.with("src", "alt");
        Card.with("src").header("h").title("t").subtitle("s").text("t").footer("f").addLink("#", "text");
        
        new Carousel();
        Carousel.with("id");
        Carousel.with(Slide.with("src"));
        Carousel.with(Slide.with("src"), Slide.with("src"));
        Carousel.with("id", Slide.with("src"));
        Carousel.with("").setShowControls(true);
        Carousel.with("").setShowIndicators(true);
        
        new Dropdown(new Toggle(""));
        Dropdown.with("toggle");
//        new Dropdown(new Dropdown.Toggle("text")).header("text").divider().add(new Link("#", "text")).add(1, new Link("#","text"));
        Dropdown.with("text");
//        Dropdown.with("text").header("t").add(Link.with("#", "text"), Link.with("#", "text")).divider().add(Link.with("#", "text"), Link.with("#", "text"));
        Dropdown.with("text", "id");
        Dropdown.with("text", "id").add(Link.with("#", "text"), Link.with("#", "text"));
        
        new Dropdown.Toggle("text");
        Dropdown.Toggle.with("text");
        Dropdown.Toggle.with("id", "text");
        Dropdown.Toggle.with("id", "text", "className");
        
        
        new Figure(new Image("src"));
        Figure.with("src");
        Figure.with("src", "alt");
        Figure.with("src", "alt", "title");
        
        new Navigation();
        Navigation.empty();
        Navigation.with(Link.with("#", "text"));
        
        new NavigationHeader();
        new NavigationHeader().add(Link.with("#", "text"));
        NavigationHeader.empty();
        NavigationHeader.with(Link.with("#", "text"));
        NavigationHeader.with("#", "");
        NavigationHeader.with("#", "text", Link.with("#", ""));
        
        new Sidebar();
        Sidebar.empty();
        Sidebar.with("header");
        Sidebar.with("header", Link.with("#", ""));
        Sidebar.with(Link.with("#", ""));
    }
    
    @Test
    public void elementsApiTest() throws Exception {
        new Link("#", "Link");
        new Link("#", "Link", "title");
        new Link("#", "Link").target("target").rel("rel");
        new Link("#","Link") {
            @Override
            public boolean isActive(HttpServletRequest request) {
                return false;
            }
            @Override
            public boolean isDisabled(HttpServletRequest request) {
                return false;
            }
            @Override
            public boolean isVisible(HttpServletRequest request) {
                return true;
            }
        };
        
        Link.with("#", "Link").rel("r").target("t");
        Link.with("#", "link", "title");
        Link.with("#", "Link", "Title", "target");
        Link.with("#", "Link", "Title", "target", "rel");
        Link link = Link.with("", "");
        link.setActive(new Condition<HttpServletRequest>() {
            
            @Override
            public boolean test(HttpServletRequest request) {
                return false;
            }
        });
        
        new Image("src");
        Image.with("src");
        Image.with("src", "alt");
        Image.with("src", "alt", "title");
    }
    
    @Test
//    @Ignore
    public void benchmark() throws Exception {
        ClassLoaderTemplateResolver res = new ClassLoaderTemplateResolver();
        res.setCacheable(false);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addTemplateResolver(res);
        
        UiDialect uiDialect = new UiDialect();
        engine.addDialect(uiDialect);
        UiTemplateResolver uiTemplateResolver = new UiTemplateResolver("bs4");
        uiTemplateResolver.setCacheable(false);
        engine.addTemplateResolver(uiTemplateResolver);
        
        long startup = System.currentTimeMillis();
        
        String html=null;
        for (int i = 0; i < 10000; i++) {
            Context context = new Context();
            context.setVariable("component", Carousel.with(Slide.with("src", "caption text"), Slide.with("src", "caption text")));
            html = engine.process("/test.html", context);
        }
        System.out.println(html);
        
        System.out.println("time: " + (System.currentTimeMillis()-startup)+"ms");
    }

}
