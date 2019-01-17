# Step08 - Distributed Tracing

## Spring Cloud Sleuth

`Spring Cloud Sleuth` adds an unique to each request in order to trace them across the components.

The main services where `Sleuth` will be used are: `Zuul Server`, `Currency Conversion`, `Currency Exchange`.

The `Sleuth` dependency is:

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
```

It is now possible to create an unique identifier for each request:

```java
import brave.sampler.Sampler;

...
public class ServiceName {

	public static void main(String[] args) {}...}

  /*
  Old Version
  public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
  */

  // Current Version
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
```

And adding a logger:

```java
Logger logger = LoggerFactory.getLogger(this.getClass());
logger.info("{}", response);
```

To trace a single request between the different services:

```
// zuul-api-gateway-server
... INFO [zuul-api-gateway-server,8d447864b8370db7,...
// currency-conversion-service
... INFO [currency-conversion-service,8d447864b8370db7,...
// currency-exchange-service
... INFO [currency-exchange-service,8d447864b8370db7,...
```

---

## RabbitMQ

`RabbitMQ` is a message broker used to manage the previous log messages.

The installation instructions for the different OS can be found at: https://www.rabbitmq.com/download.html

Even if Ubuntu repository does not have the latest version (3.6.10 vs 3.7.10) for this tutorial I'll use this version.

Ubuntu installation:

```bash
# Installation
$ sudo apt install rabbitmq-server
# Getting version and other info
$ sudo rabbitmqctl status
```

---

## Zipkin

`Zipkin` is a distributed tracing service used to manage all the log messages in order to easily debug the microservice infrastructure.

#### Installation

Sice **Zipkin UI**, **Zipkin Stream** and **Stream Rabbit** used in this tutorial are deprecated, the new installation method is:

- Download the latest [zipkin-server.jar](https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec)

- Run it:
  ```bash
  $ java -jar zipkin-server.jar
  ```
`Zipkin` is now available at: http://localhost:9411/zipkin/

For more information see the [Quick Start Guide](https://zipkin.io/pages/quickstart).

#### Connecting Microservices to Zipkin

In order to log messages in `Zipkin` format and put them  into `RabbitMQ` the following dependencies are needed:

```xml
<!-- Zipkin Not Working
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
-->
<!-- Zipkin Working -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```

It is now possible to go to `Zipkin` home page, select the `service name` and click on `find-trace` to show all traces.

Selecting a single trace it is possible to see all its requests and, by clicking on a request, see all its information.
