# Step04 - Currency Exchange Microservices

## Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.1`
- Group `com.lordalucard90.microservices`
- Artifact `currency-exchange-service`
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

## Basic Configuration

For this service a new port is needed in **application.properties**:

```
spring.application.name=currency-exchange-service
server.port=8000
```

---

## Currency Exchange Controller

As the previous services, is needed the controller:

```java
@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        return new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
    }
}
```
And a basic bean:
```java
public class ExchangeValue {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;

    // constructors

    // getters
}
```

It is now possible retrieve the `conversionMultiple` at http://localhost:8000/currency-exchange/from/USD/to/INR:

```json
{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65
}
```
