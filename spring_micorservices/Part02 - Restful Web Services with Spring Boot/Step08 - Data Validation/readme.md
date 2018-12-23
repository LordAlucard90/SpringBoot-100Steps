# Step 08 - Data Validation

## Validation

The validation starts in the **UserResource**:

```java
import javax.validation.Valid;

@RestController
public class UserResource {
  ...

  @PostMapping(path = "/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
    ...
  }

  ...
}
```

The `@Valid` decorator tells Java Validation API to check the data validation constraints declared in the **User** class:

```java
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
  ...

  @Size(min = 2)
  private String name;

  @Past
  private Date birthDate;

  ...
}
```

`@Size` specify the maximum and minimum size of a string.

`@Past` restrict a date to a past value only.

---
