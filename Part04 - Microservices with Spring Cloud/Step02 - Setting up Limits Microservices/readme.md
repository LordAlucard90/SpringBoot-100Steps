# Step 02 - Setting up Limits Microservices

## Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.1`
- Group `com.lordalucard90.microservices`
- Artifact `limits-services`
- Dependencies:
  - **Web** - contains a lot of dependencies to build web applications.
  - **DevTools** - contains some tools to develop web applications.
  - **Actuator** - contains monitoring tolls.
  - **Config Client** - contains the client to connect to Cloud Config Server.

---

## Import project into IntelliJ IDEA

1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish
7. `build` > `Build Project`
8. now you can run it

---

## Enable spring-boot-devtools livereload on IntelliJ IDEA

1. `File` > `Setting` > `Build, Execution, Deployment` > `Compiler`
2. check `Build project automatically`
3. `Help` > `Find Action..`
4. search `registry`
5. check `compiler.automake.allow.when.app.running`

---

## Base Settings

The base setting available in `src/main/resources/application.properties` are:

```
# service name
spring.application.name=limits-service
# service port
server.port=8080
```
---
## Retrieving Configuration

#### Constructor Parameters

A base approach to define the service configuration and retrieving the parameters declaring them explicitly in the constructor:

```java
public class LimitsConfiguration {
    private int maximum;
    private int minimum;

    // constructors

    // getters
}
```
And retrieve it with a rest controller:

```java
@RestController
public class LimitsConfigurationController {

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsConfiguration(){
        return new LimitsConfiguration(100, 1);
    }
}
```
#### configuration.properties

A better approach is retrieve the setting automatically from the **configuration.properties** file:

```
limit-service.minimum=99
limit-service.maximun=9999
```

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "limit-service")
public class Configuration {
  private int maximum;
  private int minimum;

  // getters and setters
}
```
`@ConfigurationProperties` tells Spring to search the configuration in the **configuration.properties** file.

`prefix` defines the prefix of the properties.

`maximum` and `minimum` define the properties names.

The values can be retrieved in this way:

```java
@RestController
public class LimitsConfigurationController {
    @Autowired
    Configuration configuration;

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsConfiguration(){
        return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
}
```
