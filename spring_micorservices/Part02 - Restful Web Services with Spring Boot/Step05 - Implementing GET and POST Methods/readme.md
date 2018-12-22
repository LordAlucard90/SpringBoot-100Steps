# Step05 - Implementing GET and POST Methods

## User Resource - Get Methods

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

---

## User Resource - Post Method

This is a simple implementation of the user creation:

```java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserResource {
    ...
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(savedUser.getId())
                                    .toUri();
        return ResponseEntity.created(location).build();
    }

}
```

`@PostMapping` associates a POST request to the `/users` url to **createUser** method.

`@RequestBody` reads the JSON from the POST request body and creates the associated User.

`@RequestBody` needs a no argument constructor for User to create a User object:

```java
public class User {
    ...

    protected User(){}

    ...
}
```

`ResponseEntity<Object>` return an entity with a HTTP status code, for a creation a **201 Created** status is needed: `ResponseEntity.created(location).build()`.

`ServletUriComponentsBuilder.fromCurrentRequest()` return the URI of the current request: `/users`.

`.path("/{id}")` appends `/{id}` to the URI, `.buildAndExpand(savedUser.getId())` assigns to `{id}` the user id value.

---

## Postman

[Postman](https://www.getpostman.com/) is a REST client for Windows, Mac and Linux. It is also available as Chrome extension.

#### Linus installation:

```bash
$ sudo snap install postman
```

#### Setting Up A POST Request

- select POST method.
- insert `http://localhost:8080/users` url.
- select `body` > `raw`
- insert the JSON object without the id:

  ```
  {
	"name": "Sempronio",
	"birthDate": "2016-12-22T14:06:53.784+0000"
  }
  ```

- select `body` > `JSON (application/json)`
- send the request

#### Response

On `Status` is reported the HTTP response status.

On `Headers` section is possible see headed informations:

```
Location        → http://localhost:8080/users/4
Content-Length  → 0
Date            → Sat, 22 Dec 2018 17:28:21 GMT
```
Where `Location` is the URI of the created resource.
