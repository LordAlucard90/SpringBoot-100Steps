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

## Generic Exception Handling

It is possible create a common exception response structure:

```java
public class ExceptionResponse {
    // response structure
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // getters
}
```

```java
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(),
                          ex.getMessage(),
                          request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(new Date(),
                          ex.getMessage(),
                          request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }
}
```

`ResponseEntityExceptionHandler` is a abstract class that can be extended to provide centralized exception handling.

`@RestController` is needed because is provided a response.

`@ControllerAdvice` allows to be used across all the other controllers.

`@ExceptionHandler` specifies the exception type handled, **Exception.class** handles all type of exceptions.
