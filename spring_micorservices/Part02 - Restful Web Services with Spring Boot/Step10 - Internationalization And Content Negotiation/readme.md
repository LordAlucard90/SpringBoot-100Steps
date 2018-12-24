# Step 10 - Internationalization And Content Negotiation

Internationalization means give to each user the content on his or her language

## LocalResolver

The `LocaleResolver` manages the locations:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {...}

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
}
```

`localeResolver.setDefaultLocale` set the default location.

---

## ResourceBundleMessageSource

The default location messages are stored in **messages.properties**, the other are stored in **messages_`[language]`.properties** in `src/main/resources` folder:

```
// messages.properties
good.morning.message=Good Morning
// messages_fr.properties
good.morning.message=Bonjour
```

The `ResourceBundleMessageSource` manages the messages:

```java

import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {...}

  ...

	@Bean
  public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
```
`messageSource.setBasename` sets the base name og the file properties locate on `resurces` folder.

---

## Using MessageSource

The correct message can be loaded at runtime:

```java
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    ...

    @GetMapping(path = "/hello-world-internationalization")
    public String helloWorldInternationalization(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning.message", null, locale);
    }
}
```

`@RequestHeader` creates a `Locale` instance based on the content of `Accept-Language`, it can be null, in this case it returns the default location.

`messageSource.getMessage` return the message in **good.morning.message** of the local file.

---

## Content Negotiation

At this moment if in the request header is specified an `Accept` content different from `application/json` a **406 Not Acceptable** status is returned.

To enable the specific format conversion like `xml` is necessary import the **jackson-dataformat-[format]** converter dependency:

```xml
<dependencies>
  <dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
  </dependency>
</dependencies>
```
Once the new dependency has been added, it will be possible to make all the requests, such as GET or POST, with the new data format.
