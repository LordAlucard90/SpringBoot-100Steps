# Step 15 - JPA Integration

## Connecting to JPA

To create a table into the database for the user the '@Entity' annotation is needed:

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ApiModel(description = "This is a social site user.")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    ...
}
```
`@Id` defines the entity id.

`@GeneratedValue` delegates the value creation.

It is possible to:

```
# show the sql in the console
spring.jpa.show-sql=true
# use a web console for the database
spring.h2.console.enabled=true
# change the database console default user and password
spring.datasource.username=sa
spring.datasource.password=
```

The database web console is available at http://localhost:8080/h2-console.

It is important change the **JDBC URL** from `jdbc:h2:~/test` to  `jdbc:h2:mem:testdb`.

It is also possible set some default test data by creating a new **data.sql** file in `src/main/resources` folder.

---

## JPA Repository

To access to the database data is necessary implement the `JpaRepository` interface:

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
```
 For the User class the `JpaRepository` manages the `User` class and the primary key is a `Integer` type.

It is now possible inject the repository:

```java
  @Autowired
  private UserRepository userRepository;
```

And use it for the queries in the controller:

#### retrieveAllUsers

```java
@GetMapping(path = "/jpa/users")
public List<User> retrieveAllUsers(){
    return userRepository.findAll();
}
```

#### retrieveSingleUsers

```java
@GetMapping(path = "/jpa/users/{id}")
public Resource<User> retrieveSingleUsers(@PathVariable int id){
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent())
        throw new UserNotFoundException("id-" + id);

    Resource<User> resource = new Resource<User>(user.get());

    ...
}
```

The **findById** method return a **Optional<Object>**, this object has two important method:

- **isPresent** - checks if the user is null.
- **get** - return the user object.

#### deleteSingleUsers

```java
@DeleteMapping(path = "/jpa/users/{id}")
public void deleteSingleUsers(@PathVariable int id){
    userRepository.deleteById(id);
}
```

The **deleteById** automatically manages the deletion of a user and it returns **void**.

So the status code of the correct deletion will be **200 OK**.

#### createUser

```java
@PostMapping(path = "/jpa/users")
public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
    User savedUser = userRepository.save(user);
    ...
}
```
Since the database tries to add a user with id 1 is necessary change the default data created id to avoid any conflict.

---

## Entity Relationships

Since as User can have multiple posts there is a many to one relationship from post to user:

```java
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    // getters and setters
}
```

Where `FetchType.LAZY` loads the user info only if the user getter method is called.

There is also a one to many relationship from the user to the posts:

It is **important** add `@JsonIgnore` to user to avoid a request loop from uses to post to user and so on when the posts are retrieved.

```java
public class User {
    ...

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    ...
}
```
Where `mappedBy` defines the related field on post class.
