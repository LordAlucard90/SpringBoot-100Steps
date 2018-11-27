# Step 04 - Redirect to Login JSP

## Create a view
Create the folders `src/main/resources/`**META-INF/resources/jsp/**.

Create a new `jsp` file **login.jsp**:

```html
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        Hello JSP!
    </body>
</html>
```

---

## Select the new view from the controller

Delete the `@ResponseBody` in the controler and return the view name.

```java
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginMessage(){
        return "login";
    }
}
```
---

## Find the view
To let **DispatcherServlet** find the `view.jsp` associated to the return of THE Controller is necessary to specify where they are in **application.properties**:

- **spring.mvc.view.prefix** - specifies the sub folder from `/META-INF/resources/` which is a default folder.
- **spring.mvc.view.suffix** - specifies the file extension type.

---

## pom.xml
To correctly parse `jsp` files is needed add a dependency to the pom:

```xml
<dependency>
  <groupId>org.apache.tomcat.embed</groupId>
  <artifactId>tomcat-embed-jasper</artifactId>
  <scope>provided</scope>
</dependency>
```

When the pom changes, the livereload is not enough to load the new updates, so is needed to restart the application.

---

## IntelliJ IDEA vs Eclipse

`jsp` files are not well supported by Spring Boot.

The first time that I tried this part it doesn't work, after many tries it downloaded the jsp page without elaborate it.

So I tried on Eclipse.. it work at the first time..

After some search i finally find a working solution:

1. Open `Maven` project tool window.
2. click on `Execute Maven Goal`.
3. Type in `Command Line` : `clean spring-boot:run`.

or

1. Open `Console` window.
2. run `mvn clean spring-boot:run`

The first solution keeps maven from IntelliJ, the second one needs maven installed on the sysyem, so I prefer the first one.

The good news is that the livereload still works and is possible restart the applications from the `run` windows keeping the maven command on.

---

## Spring Boot 2.1.0 vs Spring Boot 1.4.3

In the tutorial the `jsp` files are created on `src/main/webapp/WEB-INF/jsp/`.
