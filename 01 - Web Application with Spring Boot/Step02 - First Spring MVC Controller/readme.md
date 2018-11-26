# Step 02 - First Spring MVC Controller

## LoginController.java

```java
@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public String loginMessage(){
        return "Hello World Modified";
    }
}
```

**Annotations**:
- **Controller** - tells Spring that the class is a controller.
- **RequestMapping("** `url` **")** - links the selected `url` to the method that will process the request.
- **ResponseBody** - says to Spring to do not search for a a view named as the returned string but to elaborate that string as the page body.

## application.properties
- **logging.level.org.springframework<span>.</span>web** - set the type of informations logged in the console:
  - **INFO** - just the basic informations.
  - **DEBUG** - a more detailed set of informations.

---

## Enable spring-boot-devtools livereload on IntelliJ IDEA

1. `File` > `Setting` > `Build, Execution, Deployment` > `Compiler`
2. check `Build project automatically`
3. `Help` > `Find Action..`
4. search `registry`
5. check `compiler.automake.allow.when.app.running`

---
