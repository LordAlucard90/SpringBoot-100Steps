# Step 11 - Swagger Documentation

Swagger is a simple auto-generator of documentation and share it.

## Generation

To use swagger the dependencies above are needed:

```xml
<dependencies>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger2</artifactId>
		<version>2.9.2</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger-ui</artifactId>
		<version>2.9.2</version>
	</dependency>
</dependencies>
```
`springfox-swagger2` is the documentation api generator.

`springfox-swagger-ui` is a api html viewer.

To configure swagger a bean configuration is needed:

```java
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
```
`@Configuration` defines the class ad configuration class.

`@EnableSwagger2` tells that it is a swagger2 configuration class.

`Docket` documentation manager.

`DocumentationType` defines the type of documentation.

It is possible view the documentation at:
- http://localhost:8080/v2/api-docs
- http://localhost:8080/swagger-ui.html

---

## Documentation

The main information on `/v2/api-docs` is

- **swagger** - swagger version.
- **info** - service api information such as description, versions and license.
- **host** - the service host.
- **basepath** - the service basepath.
- **tags** - tag used to group the resources and resources methods.
- **path** - paths of all the resources exposed and the different operations exposed.
- **definitions** - contains the different element in the api.
