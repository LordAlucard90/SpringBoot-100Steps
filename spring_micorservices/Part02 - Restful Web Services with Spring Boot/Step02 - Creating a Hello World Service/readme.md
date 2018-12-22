# Step 02 - Creating a Hello World Service

## RestController

`@RestController` decorator transforms a class into a REST controller such that it can handle HTTP requests.

```java
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
  ...
}
```
---

## RequestMapping

`@RequestMapping` decorator maps a requests from a specific url, with a specific method type and a specific path, to the decorated function.

```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloWorldController {
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld(){
        return "HelloWorld";
    }
}
```
`RequestMethod` could be : **GET**, **POST**, **DELETE**, **HEAD**, **OPTIONS**, **PATCH**, **PUT**, **TRACE**.

---

## Mapping Shortcuts

`@GetMapping` is an abbreviation for `@RequestMapping(method = RequestMethod.GET)`.

```java
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }
}
```
There are also shortcuts for the other request methods.

---

## Return Beans

It is possible return a custom object like `HelloWorldBean`:

```java
@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World!");
    }
}
```
In this case is necessary implement at least one **getter**:

```java
public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    // getter
    public String getMessage() {
        return message;
    }
}
```
Otherwise is generated a **No converter found** error:

```
There was an unexpected error (type=Internal Server Error, status=500).
No converter found for return value of type: class com.lordalucard90.springboottutorials.restfulwebservices.HelloWorldBean
```

The controller will return by default a JSON object with all the getters implemented:

```json
{
  "message":"Hello World!"
}
```
---

## Enable spring-boot-devtools livereload on IntelliJ IDEA

1. `File` > `Setting` > `Build, Execution, Deployment` > `Compiler`
2. check `Build project automatically`
3. `Help` > `Find Action..`
4. search `registry`
5. check `compiler.automake.allow.when.app.running`

---
