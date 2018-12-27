# Step 14 - Versioning And Authentication

If two different consumers want two different response from the same object like:

```java
public class PersonV1 {
    private String name;

    // constructors

    // getters and setters
}
```

And:

```java
public class PersonV2 {
    private Name name;

    // constructors

    // getters and setters
}
```

Where:

```java
public class Name {
    private String firstName;
    private String lastName;

    // constructors

    // getters and setters
}
```

## URIs

An uri based solution is:

```java
@RestController
public class VersioningController {

    @GetMapping(path = "v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Pinco Pallino");
    }

    @GetMapping(path = "v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Pinco", "Pallino"));
    }
}
```
---

## Request Parameter

It is also possible set the version on a request parameter:

```java
@RestController
public class VersioningController {
  ...

  @GetMapping(value = "person", params = "version=1")
  public PersonV1 paramV1(){
      return new PersonV1("Pinco Pallino");
  }

  @GetMapping(value = "person", params = "version=2")
  public PersonV2 paramV2(){
      return new PersonV2(new Name("Pinco", "Pallino"));
  }
}
```

The request uri for version 1 will be: `/person?version=1`

---

## Header Parameter

another way is to specify the version in the header:

```java
@RestController
public class VersioningController {
  ...

  @GetMapping(value = "person", headers = "X-API-VERSION=1")
  public PersonV1 headerV1(){
    return new PersonV1("Pinco Pallino");
  }

  @GetMapping(value = "person", headers = "X-API-VERSION=2")
  public PersonV2 headerV2(){
    return new PersonV2(new Name("Pinco", "Pallino"));
  }
}
```
---

## Content Negotiation

In this case the `Accept` header parameter will be overridden:

```java
@RestController
public class VersioningController {
  ...

  @GetMapping(value = "person", produces = "application/vnd.company.app-v1+json")
  public PersonV1 producesV1(){
      return new PersonV1("Pinco Pallino");
  }

  @GetMapping(value = "person", produces = "application/vnd.company.app-v2+json")
  public PersonV2 producesV2(){
      return new PersonV2(new Name("Pinco", "Pallino"));
  }
}
```
---

## Choice Factors

- **URI Pollution** - The first two solution cause uri pollution.
- **Caching** - The last two solution cause complicated cache.
- **Browser Interactions** - The last two solution cannot be executed on common browsers.
- **Api Documentation** - Generate documentation on the first two solutions is easier than the last ones.

---

## Authentication

To add the authentication is necessary to add the dependency:

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
</dependencies>
```
On every Spring start will be generate a new password and showed in the console:

`Using generated security password: *********`

The default user is **user**.

If the password on a request is not provided will be raise a **401 Unauthorized** response.

It is possible to configure the password in the **application.properties** file:

```
// Video Version
security.user.name=lordalucard90
security.user.password=weakpassword

// My Version
spring.security.user.name=lordalucard90
spring.security.user.password=weakpassword
```

It is also possible force the authentication for management service introduce earlier:

```
// Video Version
management.security.enabled=true

// My Version
spring.management.security.enabled=true
```
