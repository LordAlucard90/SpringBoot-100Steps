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
---

## Create Multiple Run Configurations In IntelliJ-IDEA

- `Run` > `Edit Configurations`
- `+` > `Spring Boot`
  - `Name` = Configuration Name
  - `Main Class` = PackageName.ClassName
  - `Override Parameters` > `+`
    - `name` = Parameter Name
    - `value` = New Parameter Value

---

## Running Multiple Instances

Using the procedure above it is possible to override the default **application.properties** variables and run multiple service instances with different port:

```java
import org.springframework.core.env.Environment;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
```

```java
public class ExchangeValue {
    // other parameters

    private int port;

    // constructors

    // getters

    // port setter
}
```

It is now possible to retrieve the server port from the response:

```json
{
  "id":1000,
  "from":"USD",
  "to":"INR",
  "conversionMultiple":65,
  "port":8001
}
```
---

## JPA Connection

It is possible create the persistence of `CurrencyExchange` in this way:

```java
@Entity
public class ExchangeValue {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    private BigDecimal conversionMultiple;

    private int port;

    // constructors

    // getters

    // port setter
}
```

`@Column` it is necessary to change the parameters' name because sql uses them.

It is possible tell JPA to automatically implement searches:

```java
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}
```

It is now possible retrieve the currency exchange multiple:

```java
@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
```
