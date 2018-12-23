# Step 09 - HATEOAS

## Hypermedia As The Engine Of Application State

The idea is to extend the data returned with a response with other useful information.

A new dependency in `pom.xml` is needed:

```xml
<dependencies>
  ...
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-hateoas</artifactId>
  </dependency>
  ...
</dependencies>
```

Now the new information can be added:

```java
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

@RestController
public class UserResource {
  ...

  @GetMapping(path = "/users")
  public List<User> retrieveAllUsers(){...}

  @GetMapping(path = "/users/{id}")
  public Resource<User> retrieveSingleUsers(@PathVariable int id){
      User user = service.findOne(id);
      if (user == null)
          throw new UserNotFoundException("id-" + id);

      Resource<User> resource = new Resource<User>(user);

      ControllerLinkBuilder linkTo =
              ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

      resource.add(linkTo.withRel("all-users"));

      return resource;
  }

  ...
}
```

`CtrollerLinkBuilder.methodOn` specifies the method to ``

`hateoas.Resource` extends the concept of resource giving the possibility to resource links.

`ControllerLinkBuilder.linkTo` creates a link to a resource builder.

`ControllerLinkBuilder.methodOn` specifies method where get the link.

`linkTo.withRel` specifies the name of the link.

Now when a user is retrieved the response is:

```json
{
    "id": 1,
    "name": "Pinco",
    "birthDate": "2018-12-23T14:16:11.434+0000",
    "_links": {
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
}```
