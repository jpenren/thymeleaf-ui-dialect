package io.github.thymeleaf.ui.dialect;

import java.util.Collections;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import io.github.thymeleaf.ui.components.Breadcrumb.Location;
import io.github.thymeleaf.ui.components.Carousel.Slide;
import io.github.thymeleaf.ui.components.Dropdown.Divider;
import io.github.thymeleaf.ui.components.Dropdown.Toggle;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.elements.Link;
import io.github.thymeleaf.ui.internal.Strings;

class UiExpressionObjectFactory implements IExpressionObjectFactory {

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return Collections.singleton("ui");
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        return new UiExpressions(context);
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return false;
    }

    public static final class UiExpressions {
        private final IExpressionContext context;
        public final Divider divider = Divider.INSTANCE;

        public UiExpressions(IExpressionContext context) {
            this.context = context;
        }

        public Image image(String src, String alt, String title) {
            return new Image(src, i18n(alt), i18n(title));
        }

        public Image image(String src, String alt) {
            return new Image(src, i18n(alt), Strings.EMPTY);
        }

        public Image image(String src) {
            return new Image(src, Strings.EMPTY, Strings.EMPTY);
        }
        
        public Link link(String href, String text) {
            return new Link(href, i18n(text));
        }

        public Slide slide(String src) {
            return new Slide(new Image(src));
        }

        public Slide slide(String src, String caption) {
            Slide slide = new Slide(new Image(src));
            slide.setCaption(i18n(caption));
            return slide;
        }

        public Location location(String href, String text) {
            return new Location(href, i18n(text));
        }

        public Location location(String text) {
            return new Location(i18n(text));
        }
        
        public Divider divider() {
            return Divider.INSTANCE;
        }
        
        public Toggle toggle(String text) {
            return toggle(text, null, null);
        }
        
        public Toggle toggle(String text, String id) {
            return toggle(text, id, null);
        }
        
        public Toggle toggle(String text, String id, String className) {
            return Toggle.toggle(i18n(text), id, className);
        }
        
        private String i18n(String str) {
            if(context instanceof ITemplateContext) {
                final ITemplateContext templateContext = (ITemplateContext)context;
                String msg = templateContext.getMessage(null, str, null, true);
                return msg==null ? str : msg;
            }
            
            return str;
        }
        
    }

}