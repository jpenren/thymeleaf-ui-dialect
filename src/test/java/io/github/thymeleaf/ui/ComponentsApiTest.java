package io.github.thymeleaf.ui;

import static io.github.thymeleaf.ui.Components.alert;
import static io.github.thymeleaf.ui.Components.alertDismissible;
import static io.github.thymeleaf.ui.Components.badge;
import static io.github.thymeleaf.ui.Components.breadcrumb;
import static io.github.thymeleaf.ui.Components.button;
import static io.github.thymeleaf.ui.Components.buttonGroup;
import static io.github.thymeleaf.ui.Components.buttonToggle;
import static io.github.thymeleaf.ui.Components.card;
import static io.github.thymeleaf.ui.Components.carousel;
import static io.github.thymeleaf.ui.Components.dropdown;
import static io.github.thymeleaf.ui.Components.figure;
import static io.github.thymeleaf.ui.Components.image;
import static io.github.thymeleaf.ui.Components.link;
import static io.github.thymeleaf.ui.Components.location;
import static io.github.thymeleaf.ui.Components.navigation;
import static io.github.thymeleaf.ui.Components.navigationHeader;
import static io.github.thymeleaf.ui.Components.sidebar;
import static io.github.thymeleaf.ui.Components.slide;

import javax.servlet.http.HttpServletRequest;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import io.github.thymeleaf.ui.components.Dropdown;
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentsApiTest.class);
    
    @Test
    public void componentsApiTest() throws Exception {
        new Alert("text");
        new Alert("Alert text").setDismissible(true);
        
        alert("");
        alertDismissible("");
        
        new Badge("3");
        badge("3");
        badge("#", "text");
        
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.add(new Location("#", "text"));
        breadcrumb(location(""));
        breadcrumb().add(location("",""));
        
        Button button = new Button("text");
        button.setHref("#");
        
        button("text");
        button("text", "type");
        buttonToggle("text");
        
        new ButtonGroup();
        new ButtonGroup().add(button("text"));
        new ButtonGroup().add(button("text"), button("text"));
        buttonGroup(); //.empty();
        buttonGroup().add(button("text"));
        buttonGroup().add(button("text"), button("text"));
        buttonGroup(button("text"));
        buttonGroup(button("text"), button("text"));
        
        new Card(new Image("src"));
        card("src");
        card("src", "alt");
        card("src").header("h").title("t").subtitle("s").text("t").footer("f").addLink("#", "text");
        
        new Carousel();
        carousel("id");
        carousel(slide("src"));
        carousel(slide("src"), slide("src"));
        carousel("id", slide("src"));
        carousel("").setShowControls(true);
        carousel("").setShowIndicators(true);
        
        new Dropdown(new Toggle(""));
        dropdown("toggle");
        dropdown("text");
        dropdown("text", "id");
        dropdown("text", "id").add(link("#", "text"), link("#", "text"));
        
        new Dropdown.Toggle("text");
        Dropdown.Toggle.toggle("text");
        Dropdown.Toggle.toggle("id", "text");
        Dropdown.Toggle.toggle("id", "text", "className");
        
        
        new Figure(new Image("src"));
        figure("src");
        figure("src", "alt");
        figure("src", "alt", "title");
        
        new Navigation();
        navigation();
        navigation(link("#", "text"));
        
        new NavigationHeader();
        new NavigationHeader().add(link("#", "text"));
        navigationHeader();
        navigationHeader(link("#", "text"));
        navigationHeader("#", "");
        navigationHeader("#", "text", link("#", ""));
        
        new Sidebar();
        sidebar();
        sidebar("header");
        sidebar("header", link("#", ""));
        sidebar(link("#", ""));
    }
    
    @Test
    public void elementsApiTest() throws Exception {
        new Link("#", "Link");
        link("", "", "");
        Link link = new Link("#", "Link");
        link.setRel("rel");
        link.setTarget("target");
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
        
        link("#", "Link");
        link("#", "link", "title");
        link("#", "Link", "Title", "target");
        link("#", "Link", "Title", "target", "rel");
        
        new Image("src");
        image("src");
        image("src", "alt");
        image("src", "alt", "title");
    }
    
    @Test
    @Ignore
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
            context.setVariable("component", carousel(slide("src", "caption text"), slide("src", "caption text")));
            html = engine.process("/test.html", context);
        }
        
        LOGGER.info(html);
        
        LOGGER.info("time: " + (System.currentTimeMillis()-startup)+"ms");
    }

}
