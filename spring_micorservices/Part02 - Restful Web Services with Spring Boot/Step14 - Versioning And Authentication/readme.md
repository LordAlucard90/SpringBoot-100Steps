## Step 14 - Versioning And Authentication

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

# URIs

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
