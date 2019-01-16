# Step07 - Zuul

## Creating Zuul Service

`Zuul` is an API Gateways that can be used for:
- Authentication, Authorization and Security
- Rate Limits
- Fault Tolerance
- Service Aggregation

#### Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.2`
- Group `com.lordalucard90.microservices`
- Artifact `zuul-api-gateway-server`
- Dependencies:
  - **DevTools** - contains some tools to develop web applications.
  - **Actuator** - contains monitoring tolls.
  - **Zuul** - contains the Zuul Server.
  - **Eureka Discovery** - contains the Eureka dependencies.


#### Import project into IntelliJ IDEA

1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish
7. `build` > `Build Project`
8. now you can run it


#### Enable spring-boot-devtools livereload on IntelliJ IDEA

1. `File` > `Setting` > `Build, Execution, Deployment` > `Compiler`
2. check `Build project automatically`
3. `Help` > `Find Action..`
4. search `registry`
5. check `compiler.automake.allow.when.app.running`

#### Basic Configuration

The base **application.properties** configuration is:

```
spring.application.name=zuul-api-gateway-server
server.port=8765

eureka.client.service-url.default-zone=http://localhost:8761/eureka
```
---

## Activating Zuul Porxy

The first is enabling `ZuulProxy`:

```java
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApiGatewayServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayServerApplication.class, args);
	}
}
```
---

## Logging Filter

It is possible create a custom logging filter implementing `ZuulFilter` interface:

```java
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggingFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri is {}", request, request.getRequestURI());
        return null;
    }
}
```

The filter has to implement this methods:
- **filterOrder** - returns the filter priority.
- **shouldFilter** - returns `true` if the filter must be used for the current request.
- **filterType** - return the moment when the filter must be used:
    - `pre` - before the request.
    - `post` - after the request.
    - `error` - if an error occurs.
- **run** - contains the filter logic.

---

## Sending Requests Through Zuul

It is possible to send a request to a service through Zuul using the uri:

- http://localhost:8765/{service-name}/{uri}

For example:
- http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion-service/currency-converter-feign/from/EUR/to/INR/quantity/10000

#### Using Zuul With Currency Conversion Server

To use Zuul Proxy in the Currency Conversion Server is needed:

```java
@FeignClient(name = "zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
```
Change the Feign Client reference to the Zuul Server:

`@FeignClient(name = "zuul-api-gateway-server")`

Update the Proxy Mapping reference adding the server name at the beginning:

`@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")`
