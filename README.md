# Thymeleaf UI Dialect
Thymeleaf dialect to create interface components bootstrap like, provides some attributes to create user interface (html) components.

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


This will introduce the `ui` namespace and the new attribute processor `render` to create html components.


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

Html template:

```html
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:ui="http://www.thymeleaf.org/ui">
<head>
...
</head>
<body>

    <div ui:render="${alert}"></div>
    <span ui:render="${badge}"></span>
    <nav ui:render="${breadcrumb}"></nav>
    <div ui:render="${card}"></div>
    <div ui:render="${carousel}"></div>
    <div ui:render="${dropdown}"></div>

</body>
</html>
```


Themes
--------

Custom components
-------
