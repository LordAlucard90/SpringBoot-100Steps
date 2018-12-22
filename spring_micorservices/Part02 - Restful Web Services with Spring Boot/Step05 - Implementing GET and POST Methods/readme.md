# Step05 - Implementing GET and POST Methods

## User Resource

This is a simple example of a User Resource class:

```java
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveSingleUsers(@PathVariable int id){
        return service.findOne(id);
    }

}
```

`@Autowired` tell spring to inject the **UserDaoService** into **UserResource** as private filed named **service**.

---

## JSON Date Formatting

The dates by default are passed with the timestamp value, to retrieve a human readable format can set in **application.properties**:

```
spring.jackson.serialization.write-dates-as-timestamps=false
```
