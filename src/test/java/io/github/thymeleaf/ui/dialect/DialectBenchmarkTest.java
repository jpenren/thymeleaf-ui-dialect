package io.github.thymeleaf.ui.dialect;

import static io.github.thymeleaf.ui.Components.carousel;
import static io.github.thymeleaf.ui.Components.dropdown;
import static io.github.thymeleaf.ui.Components.link;
import static io.github.thymeleaf.ui.Components.navigationHeader;
import static io.github.thymeleaf.ui.Components.sidebar;
import static io.github.thymeleaf.ui.Components.slide;
import static io.github.thymeleaf.ui.Components.submenu;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.github.thymeleaf.ui.components.Alert;
import io.github.thymeleaf.ui.components.Breadcrumb;
import io.github.thymeleaf.ui.components.Card;
import io.github.thymeleaf.ui.components.Dropdown;
import io.github.thymeleaf.ui.components.Navigation;
import io.github.thymeleaf.ui.components.NavigationHeader;
import io.github.thymeleaf.ui.components.Sidebar;
import io.github.thymeleaf.ui.elements.Link;
import io.github.thymeleaf.ui.components.Breadcrumb.Location;

public class DialectBenchmarkTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DialectBenchmarkTest.class);
    
    @Test
//    @Ignore
    public void benchmark() throws Exception {
        ClassLoaderTemplateResolver res = new ClassLoaderTemplateResolver();
        res.setCacheable(false);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addTemplateResolver(res);

        UiDialect uiDialect = new UiDialect();
        engine.addDialect(uiDialect);
        ComponentTemplateResolver uiTemplateResolver = new ComponentTemplateResolver("default");
        uiTemplateResolver.setOrder(1);
        uiTemplateResolver.setCacheable(false);
        engine.addTemplateResolver(uiTemplateResolver);

        long startup = System.currentTimeMillis();

        String html = null;
        for (int i = 0; i < 1; i++) {
            Context context = new Context();
            
            Alert alert = new Alert("From java");
            alert.setId("theid");
            alert.setDismissible(true);
            context.setVariable("theAlert", alert);
            
            Card card = new Card();
            card.setId("cardId");
            card.setHeader("my header");
            context.setVariable("theCard", card);
            
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.add(new Location("href","text"));
            context.setVariable("breadcrumb", breadcrumb);
            
            Navigation nav = new Navigation();
            nav.add(new Link("href","alt"));
            nav.add(new Link("href","alt"));
            context.setVariable("nav", nav);
            
            NavigationHeader header = navigationHeader("#", "navheader", link("#", "link"));
            Dropdown dropdown = dropdown("tog", "t1");
            dropdown.add(link("#", "Link"));
            dropdown.add(link("#", "Link2"));
            dropdown.divider();
            dropdown.add(link("#", "Link"));
            header.add(dropdown);
            context.setVariable("navigationHeader", header);
            
            Sidebar sidebar = sidebar("Header", link("#", "link"), link("#", "link"));
            sidebar.add(submenu("Menu", link("#", "link"), link("#", "Link")));
            context.setVariable("sidebar", sidebar);
            
            context.setVariable("msg", "Mensaje molón");
            context.setVariable("carousel", carousel(slide("src", "caption text"), slide("src", "caption text")));
            html = engine.process("/test.html", context);
        }

        LOGGER.info(html);

        LOGGER.info("time: " + (System.currentTimeMillis() - startup) + "ms");
    }
}
