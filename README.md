# Thymeleaf UI Dialect
Thymeleaf dialect to create interface components, provides a simple way to define reusable components and some html attributes to render user interface (html) components.

See demo project: https://github.com/jpenren/thymeleaf-ui-dialect-demo

Usage
-----

Add the Maven dependency to your project:
```xml
<dependency>
	<groupId>io.github.jpenren</groupId>
	<artifactId>thymeleaf-ui-dialect</artifactId>
	<version>1.1.0</version>
</dependency>
```

This library uses Spring Boot autoconfiguration feature, if required you can define this dialect manually:
```java
templateEngine.addDialect(new UiDialect());
String theme = "default";
templateEngine.addTemplateResolver(new ComponentTemplateResolver(theme));
```

Components
-----

Enabling this dialect will introduce the `ui` namespace and a new set of attribute processors for available components:

**Alert**

Template:
```html
<div ui:alert="#{msg.key}" ui:dismissible="true"></div>
```
Output:
```html
<div class="alert alert-primary alert-dismissible fade show" role="alert">
  Alert message
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
```

**Badge**
Template:
```html
<span ui:badge="value"></span>
```
Output:
```html
<span class="badge badge-secondary">value</span>
```
Template:
```html
<span ui:badge="value" ui:href="'http://www.example.org'"></span>
```
Output:
```html
<a class="badge badge-secondary" href="http://www.example.org">value</a>
```

**Breadcrumb**

Template:
```html
<div ui:breadcrumb ui:locations="${ {#ui.location('http://url','Home'), #ui.location('Current page')} }"></div>
```
Output:
```html
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a href="http://url">Home</a>
    </li>
    <li class="breadcrumb-item active" aria-current="page">
      Current page
    </li>
  </ol>
</nav>
```

**Button**

Template:
```html
<button ui:button ui:toggle="true" ui:active="true"></button>
```
Output:
```html
<button type="button" class="btn btn-primary active" data-toggle="button"></button>
```

**Card**

Template:
```html
<div ui:card ui:header="Header" ui:image="${#ui.image('http://url','alt text','title')}" ui:footer="Footer"></div>
```
Output:
```html
<div class="card">
    <div class="card-header">Header</div>
    <img class="card-img-top" src="http://url" alt="alt text">
    <div class="card-body">
    </div>
    <div class="card-footer text-muted">Footer</div>
</div>
```

**Carousel**

Template:
```html
<div ui:carousel ui:slides="${ {#ui.slide('#{http://url}','Caption'), #ui.slide('http://url')} }" ui:show-controls="true" ui:show-indicators="true"></div>
```
Output:
```html
<div class="carousel slide" data-ride="carousel" id="car_1551028552310">
  <ol class="carousel-indicators">
    <li data-slide-to="0" data-target="#car_1551028552310" class="active"></li>
    <li data-slide-to="1" data-target="#car_1551028552310"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="#{http://url}">
      <div class="carousel-caption d-none d-md-block">??Caption_es_ES??</div>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="http://url">
    </div>
  </div>
  <a class="carousel-control-prev" href="#car_1551028552310" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#car_1551028552310" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
```

**Dropdown**

Template:
```html
<div ui:dropdown="#{toggle}" ui:header="#{header}" ui:items="${ {#ui.link('href', 'text'), #ui.divider, #ui.link('rerf','tex')} }"></div>
```
Output:
```html
<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Toggle</button>
  <div class="dropdown-menu">
        <h6 class="dropdown-header">Header</h6>
        <a class="dropdown-item" href="href">Text</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="rerf">Text</a>
  </div>
</div>
```

**Figure**

Template:
```html
<div ui:figure="${#ui.image('http://url')}" ui:caption="#{caption}"></div>
```
Output:
```html
<figure class="figure">
    <img src="http://url" class="figure-img img-fluid">
    <figcaption class="figure-caption">Caption text</figcaption>
</figure>
```

**Navigation**

Template:
```html
<div ui:nav ui:items="${ {#ui.link('href', 'text'),#ui.link('href','text')} }"></div>
```
Output:
```html
<ul class="nav">
    <li class="nav-item" >
      <a class="nav-link" href="href">Text</a>
    </li>
    <li class="nav-item" >
      <a class="nav-link" href="href">Text</a>
    </li>
</ul>
```

**Navigation header**

Template:
```html
<nav ui:nav-header ui:brand="${ #ui.link('href','text') }" ui:items="${ {#ui.link('ref','alt')} }"></nav>
```
Output:
```html
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="href">Text</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="href">Text</a>
      </li>
    </ul>
  </div>
</nav>
```

Example with Java code:
```Java
import static io.github.thymeleaf.ui.Components.*;
...
@ControllerAdvice
public class UiControllerAdvice {
    
    @ModelAttribute
    public NavigationHeader addNavigationHeader(Locale locale) {
        NavigationHeader header = navigationHeader("#", "navheader", link("#", "text"));
        Dropdown dropdown = dropdown("toggle", "t1");
        dropdown.add(link("#", "text"));
        dropdown.add(link("#", "text"));
        dropdown.divider();
        dropdown.add(link("#", "text"));
        header.add(dropdown);
        
        return header;
    }
}
```
Template:
```html
<nav ui:nav-header></nav>
```
Output:
```html
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="href">Text</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="#">Text</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="t1" aria-expanded="false" href="#" role="button" data-toggle="dropdown" aria-haspopup="true">Toogle</a>
        <div class="dropdown-menu" aria-labelledby="t1">
          <a href="#" class="dropdown-item">Text</a>
          <a href="#" class="dropdown-item">Text</a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">Text</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="href">Text</a>
      </li>
    </ul>
  </div>
</nav>
```

**Sidebar**

Template:
```html
<nav ui:sidebar="mySidebar" ui:header="#{header}" ui:items="${ {#ui.link('href','text'), #ui.link('href','text')} }"></nav>
```
Output:
```html
<nav class="sidebar-nav">
  <div class="sidebar-header">Header</div>
  <ul>
    <li>
      <a href="href">Text</a>
    </li>
    <li>
      <a href="href">Text</a>
    </li>
  </ul>
</nav>
```

Exmple with Java code:
```Java
import static io.github.thymeleaf.ui.Components.*;
...
@ControllerAdvice
public class UiControllerAdvice {

    @ModelAttribute
    public Sidebar addSidebar() {
        Sidebar sidebar = sidebar("Header", link("#", "text"), link("#", "text"));
        sidebar.add(submenu("Menu", link("#", "text"), link("#", "text")));
            
        return sidebar;
    }
}
```
Template
```html
<nav ui:sidebar></nav>
```
Output:
```html
<nav class="sidebar-nav">
  <div class="sidebar-header">Header</div>
  <ul>
    <li>
      <a href="#">text</a>
    </li>
    <li>
      <a href="#">text</a>
    </li>
    <li>
      <a href="#menu_1551030034192" data-toggle="collapse" class="dropdown-toggle" aria-expanded="false">Menu</a>
      <ul id="menu_1551030034192" class="sidebar-nav-menu collapse">
        <li>
          <a href="#">text</a>
        </li>
        <li>
          <a href="#">text</a>
        </li>
      </ul>
    </li>
  </ul>
</nav>
```


Create UI components in Java
--------

Spring MVC @Controller:

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
<body>
    <div ui:alert="${alert}"></div>
    <span ui:alert="${badge}"></span>
    <span class="badge badge-pill badge-primary" ui:alert="${badge2}"></span>
    <nav ui:breadcrumb="${breadcrumb}"></nav>
    <div ui:card="${card}"></div>
    <div ui:carousel="${carousel}"></div>
    <div ui:dropdown="${dropdown}"></div>
</body>
</html>
```

Note: the <code>ui:render</code> tag is able to render any Renderable object. Using <code>ui:render="${alert}"</code> produces the same output as <code>ui:alert="${alert}"</code>

<img src="https://raw.githubusercontent.com/jpenren/thymeleaf-ui-dialect/master/doc/components.jpg" width="600">


Themes
--------

Any component has an html template with some Thymeleaf logic, these templates are located under `templates/ui/themes/[theme]/[class-name]-component.html`. 

The default theme is `default` based on Bootstrap 4.


Custom components
-------

Define your custom components just extending `io.github.thymeleaf.ui.Component` and create the html template, an example of a new component:

```java
public class CustomComponent extends Component {
    private final String message;

    public CustomComponent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
```

Template file for this component `src/main/resources/templates/ui/themes/[theme]/custom-component-component.html`:
```html
<div>
    <strong th:text="*{message}"></strong>
</div>
```

Spring MVC @Controller

```java
@GetMapping("/index")
public String index(ModelMap model) {
    model.addAttribute(new CustomComponent("Hello world!"));
    
    return "index";
}
```

Application html template:

```html
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:ui="http://www.thymeleaf.org/ui">
<body>
    <div ui:render="${customComponent}"></div>
</body>
</html>
```
