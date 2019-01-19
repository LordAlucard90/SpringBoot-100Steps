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
- **consumes** - the input data type.
- **produces** - the output data type.

---

## Enhancing Documentation

It is possible to improve the general documentation with:

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "LordAlucard90",
            "https://github.com/LordAlucard90",
            "email@example.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Simple Social Site",
            "This is a simple social site application.",
            "1.0",
            "urn:tos",
             DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

}
```

`apiInfo` extends the base application api info.

`produces`, `consumes` extend the input and output type info.

It is also possible extend the model documentation info:

```java
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is a social site user.")
public class User {
    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(notes = "Name should have at least 2 characters.")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birth date should be in the past.")
    private Date birthDate;

		...
}
```

`@ApiModel` adds general info about the model.

`@ApiModelProperty` adds specific info about model fields.
