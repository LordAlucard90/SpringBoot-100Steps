# Step06 - Ribbon And Eureka

## Ribbon

`Ribbon` helps to distribute the request between different microservices instances.

Its dependency is:

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-ribbon</artifactId>
</dependency>
```
It is now possible to omit the `Feign` url:
```Java
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
```

`@RibbonClient` defines the service name

Using that name it is possible to set the called server in **application.properties**:

```
currency-exchange-service.ribbon.listOfServers=http://localhost:8000,http://localhost:8001
```
---

## Creating Eureka Service

`Eureka` provides a service where every service can register itself (service registration).

When a service wants to talk to another it asks to `Eureka` to get the current services running (service discovery).

#### Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.1`
- Group `com.lordalucard90.microservices`
- Artifact `eureka-naming-server`
- Dependencies:
  - **Eureka Server** - contains the Eureka Server.
  - **DevTools** - contains some tools to develop web applications.
  - **Actuator** - contains monitoring tolls.
  - **Config Client** - contains the client to connect to Cloud Config Server.


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
spring.application.name=eureka-naming-server
server.port=8761
# Temporary Configuration
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

An high level view of the service is available at http://localhost:8761/

The main displayed are:
- System Status
- DS Replicas
- Instances currently registered with Eureka
- General Info
- Instance Info

---

## Registering Services To Eureka

The client needs the dependancy:

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```

It is necessary tell where Eureka can be founded:
```
  eureka.client.service-url.default-zone=http://localhost:8761/eureka
```

Eureka registration is enable with `@EnableDiscoveryClient
`:
```java
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableFeignClients("com.lordalucard90.microservices.currencyconversionservice")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}
```
And

```java
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}
}
```
To enable Ribbon to balance the load using Eureka it is necessary comment the `listOfServers` property in **application.properties**
```
eureka.client.service-url.default-zone=http://localhost:8761/eureka

#currency-exchange-service.ribbon.listOfServers=http://localhost:8000,http://localhost:8001
```
---
