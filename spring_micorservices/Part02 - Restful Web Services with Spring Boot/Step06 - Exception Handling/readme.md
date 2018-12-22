# Step 06 - Exception Handling

## 404

At this time if a resource that does not exists is requested through GET `/users/99` the returns status is 200 with an empty response.

```java
public class UserResource {
    ...

    @GetMapping(path = "/users/{id}")
    public User retrieveSingleUsers(@PathVariable int id){
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
        return user;
    }

    ...
}
```

In **retrieveSingleUsers** the method is required, if the user is null, to throw a custom **UserNotFoundException**:

```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

`@ResponseStatus` return the correct HTTP status Code.

The respond body is something like:

```json
{
    "timestamp": "2018-12-22T18:08:15.212+0000",
    "status": 404,
    "error": "Not Found",
    "message": "id-99",
    "trace": "...",
    "path": "/users/99"
}
```
