# Step 12 - Monitoring APIs

## Monitoring

The dependencies used to monitor a Spring application are:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-rest-hal-browser</artifactId>
</dependency>
```
`spring-boot-starter-actuator` provides monitoring for the services.

`spring-data-rest-hal-browser` provides an HTML browsing interface for the API monitoring.

The `HAL Browser` is available at http://localhost:8080/ or http://localhost:8080/browser/index.html#/.

---

## Notes

In the tutorial is used `management.security.enabled=false` on **application.properties** to access to a private section of `HAL Browser`. In the current version the option is deprecated.

Both `health` and `metrics` paths are changed.

#### New Configuration

The new configuration (not for production) needs a class implementation:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// TODO remove in production
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2_console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
```
