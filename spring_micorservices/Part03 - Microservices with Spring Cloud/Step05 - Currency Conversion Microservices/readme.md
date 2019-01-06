# Step05 - Currency Conversion Microservices

## Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.1`
- Group `com.lordalucard90.microservices`
- Artifact `currency-conversion-service`
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
spring.application.name=currency-conversion-service
server.port=8100
```

---

## Currency Conversion Controller

As the previous services, is needed the controller:

```java
@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){
        return new CurrencyConversionBean(1L, from, to, BigDecimal.ONE, quantity, quantity, 8100);
    }

}
```
And a basic bean:
```java
public class CurrencyConversionBean {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private int port;

    // constructors

    // getters and setters
}
```

It is now possible retrieve the `conversionMultiple` at http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000:

```json
{
  "id": 1,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "quantity": 1000,
  "totalCalculatedAmount": 1000,
  "port": 8100
}
```
---

## Invoking Currency Exchange Microservices

It is possible to load the response from another service as a `ResponseEntity`:

```java
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables
        );

        CurrencyConversionBean response = responseEntity.getBody();

        return new CurrencyConversionBean(
                response.getId(),
                from,
                to,
                response.getConversionMultiple(),
                quantity,
                quantity.multiply(response.getConversionMultiple()),
                response.getPort());
    }
}
```
`RestTemplate().getForEntity` execute a GET request on an URL, it aspects an `CurrencyConversionBean.class` object as response and set `uriVariables` as variable in the URL.

If `currency-exchange-service` is running the response from http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000 is:

```json
{
  "id":1001,
  "from":"USD",
  "to":"INR",
  "conversionMultiple":65.00,
  "quantity":1000,
  "totalCalculatedAmount":65000.00,
  "port":8000
}
```
---

## Improving Invokes With Feign REST Client

The dependency needed by `Feign` is:

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
    <version>1.4.6.RELEASE</version>
  </dependency>
</dependencies>
```
It is now possible create a service proxy for the currency exchange service in this way:

```java
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
```

The controller becomes:

```java
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    ...

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionBean(
                response.getId(),
                from,
                to,
                response.getConversionMultiple(),
                quantity,
                quantity.multiply(response.getConversionMultiple()),
                response.getPort());
    }
}
```

It is now possible retrieve the same response from http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/1000:

```json
{
  "id":1001,
  "from":"USD",
  "to":"INR",
  "conversionMultiple":65.00,
  "quantity":1000,
  "totalCalculatedAmount":65000.00,
  "port":8000
}
```
