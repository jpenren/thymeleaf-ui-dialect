package io.github.thymeleaf.ui;

import org.junit.Test;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
import io.github.thymeleaf.ui.dialect.UiDialect;
import io.github.thymeleaf.ui.dialect.UiTemplateResolver;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.elements.Link;

public class ComponentsApiTest {
    
    @Test
    public void componentsApiTest() throws Exception {
        new Alert("text");
        Alert alert = new Alert("Alert text").dismissible();
        
        new Badge("3");
        Badge badge = new Badge("#", "3");
        
        new Breadcrumb().location("#", "Link").location("text");
        new Breadcrumb().locations(new Breadcrumb.Location("#", "Link"));
        
        NavigationHeader link = new NavigationHeader().link(null);
        
        
    }
    
    @Test
    public void elementsApiTest() throws Exception {
        
        new Link("#", "Link");
        new Link("#", "Link", "Title");
        new Link("#", "Link", "Title", "target");
        new Link("#", "Link", "Title", "target", "rel");
        
        new Image("src");
        new Image("src", "alt");
        
        new Dropdown.Toggle("text");
        new Dropdown.Toggle("id", "text");
        new Dropdown.Toggle("id", "text", "className");
        new Dropdown(new Dropdown.Toggle("text")).header("text").divider().link(new Link("#", "text")).link(1, new Link("#","text"));
        new Dropdown("id", "text").divider().links(new Link("#","text"));
    }
    
    @Test
    public void testName() throws Exception {
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
        for (int i = 0; i < 1000; i++) {
            Context context = new Context();
            context.setVariable("alert", new Sidebar().links(new Link("#", "Link"), new Link("#", "Link")));
            html = engine.process("/test.html", context);
        }
        System.out.println(html);
        
        System.out.println("time: " + (System.currentTimeMillis()-startup)+"ms");
    }

}
