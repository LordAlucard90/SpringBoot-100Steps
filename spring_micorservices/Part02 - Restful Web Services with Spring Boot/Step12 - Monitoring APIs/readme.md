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

I have to search for them.
