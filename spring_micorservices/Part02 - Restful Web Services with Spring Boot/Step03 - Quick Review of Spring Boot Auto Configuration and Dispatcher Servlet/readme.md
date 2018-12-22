# Quick Review of Spring Boot Auto Configuration and Dispatcher Servlet

## Debug Mode

In **src/main/resources/application.properties** can be set some project properties like:

```
logging.level.org.springframework = debug
```

The `debug` level will show more log in the console.

---

## Auto-Configuration Report

In the current version is named `CONDITIONS EVALUATION REPORT`.

The report id divided in: `Positive matches`, `Negative matches`, `Exclusions`, `Unconditional classes`.

Important elements of the report are:

- **DispatcherServletAutoConfiguration** - configure the Dispatcher Servlet.
- **ErrorMvcAutoConfiguration** - configure the error pages.
- **HttpMessageConvertersAutoConfiguration** - manage the automatic conversion of request from bean to JSON.
- **JacksonAutoConfiguration.Jackson2ObjectMapperBuilderCustomizerConfiguration** - makes the conversion from JSON to beans and form beans to JSON.

---

## Dispatcher Servlet

The Dispatcher Servlet maps all the different url in the application:

```
Mapping servlets: dispatcherServlet urls=[/]
```
