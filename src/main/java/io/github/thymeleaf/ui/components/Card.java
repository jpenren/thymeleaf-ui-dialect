package io.github.thymeleaf.ui.components;

import java.util.ArrayList;
import java.util.List;

import io.github.thymeleaf.ui.Component;
import io.github.thymeleaf.ui.elements.Image;
import io.github.thymeleaf.ui.elements.Link;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Card extends Component {
    private final List<Link> links = new ArrayList<>();
    private final Image image;
    private String header;
    private String title;
    private String subtitle;
    private String text;
    private String footer;
    
    public Card(String src, String alt) {
        this.image = new Image(src, alt);
    }
    
    public Card link(String href, String text) {
        links.add(new Link(href, text));
        return this;
    }
    
    public Card link(Link link) {
        links.add(link);
        return this;
    }
    
}
