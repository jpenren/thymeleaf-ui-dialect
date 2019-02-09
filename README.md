# Thymeleaf UI Dialect
Thymeleaf dialect to create interface components, provides a simple way to define reusable components and some html attributes to render user interface (html) components.

Usage
-----

Add the Maven dependency to your project:
```xml
<dependency>
	<groupId>io.github.jpenren</groupId>
	<artifactId>thymeleaf-ui-dialect</artifactId>
	<version>(in progress)</version>
</dependency>
```

This library uses Spring Boot autoconfiguration feature, if required you can define this dialect manually:
```java
String theme = "bs4";
templateEngine.addDialect(new UiTemplateResolver(theme));
```

Enabling this dialect will introduce the `ui` namespace and the new attribute processor `render` to use html components.


Examples
--------

Spring MV Controller:

```java
import static io.github.thymeleaf.ui.Components.*;
...

@GetMapping("/index")
public String index(ModelMap model){
	
    // Alert
    model.addAttribute("alert", alert("This is an alert"));	
    
    //Badge
    model.addAttribute("badge", badge("4"));
    model.addAttribute("badge2", badge("5"));
    
    //Breadcrumb
    Breadcrumb breadcrumb = breadcrumb(location("/", "home"), location("/", "admin"), location("current-page"));
    model.addAttribute(breadcrumb);
    
    //Card
    Card card = card("http://[image-location]", "Image alt");
    card.setHeader("Card header");
    card.setTitle("Card title");
    card.setText("Some quick example text");
    card.addLink("#", "Launch");
    model.addAttribute(card);
    
    //Carousel
    Carousel carousel = carousel("demoCarousel", slide("http://[img-location]"), slide("http://img-location"));
    carousel.setShowControls(true);
    carousel.setShowIndicators(true);
    model.addAttribute(carousel);
    
    //Dropdown
    Dropdown dropdown = dropdown("Toggle");
    dropdown.add(link("#", "First Link"));
    dropdown.setHeader("My header");
    model.addAttribute(dropdown);
    
    return "index";
}
```

Application html template:

```html
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:ui="http://www.thymeleaf.org/ui">
<head>
...
</head>
<body>

    <div ui:render="${alert}"></div>
    <span ui:render="${badge}"></span>
    <span class="badge badge-pill badge-primary" ui:render="${badge2}"></span>
    <nav ui:render="${breadcrumb}"></nav>
    <div ui:render="${card}"></div>
    <div ui:render="${carousel}"></div>
    <div ui:render="${dropdown}"></div>

</body>
</html>
```

![alt text](https://raw.githubusercontent.com/jpenren/thymeleaf-ui-dialect/master/doc/components.jpg "UI Components")

Themes
--------

Any component has an html template with some Thymeleaf functions, these templates are located under `templates/ui/[theme]/components`. 

The default theme is bs4 (Bootstrap 4)


Custom components
-------

Define your custom components just extending `io.github.thymeleaf.ui.Component` and create the html template, an example of a new component:

```java
public class CustomComponent extends Component {

}

@GetMapping("/index")
public String index(ModelMap model) {
    model.addAttribute(new CustomComponent());
    
    return "index";
}
```

Template file for this component `src/main/resources/templates/ui/bs4/components/custom-component.html`:
```html
<div>
    <strong>Hello world!</strong>
</div>
```
