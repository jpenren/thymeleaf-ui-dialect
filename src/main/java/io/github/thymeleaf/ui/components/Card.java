package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Card extends Component {
    private final List<Link> links = new ArrayList<>();
    private Image image;
    private String header;
    private String title;
    private String subtitle;
    private String text;
    private String footer;
    
    public Card(Image image) {
        this.image = image;
    }
    
    public Card header(String header) {
        this.header = header;
        return this;
    }
    
    public Card title(String title) {
        this.title = title;
        return this;
    }
    
    public Card subtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }
    
    public Card text(String text) {
        this.text = text;
        return this;
    }
    
    public Card footer(String footer) {
        this.footer = footer;
        return this;
    }
    
    public Card addLink(String href, String text) {
        return add(Link.with(href, text));
    }
    
    public Card add(Link link) {
        links.add(link);
        return this;
    }
    
    public static Card empty() {
        return new Card();
    }
    
    public static Card with(String src) {
        return with(src, null);
    }
    
    public static Card with(String src, String alt) {
        return new Card(Image.with(src, alt));
    }
    
}
