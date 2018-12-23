# Step 06 - Exception Handling

## Delete

The user deletion must be implemented in the **UserDaoService**:

```java
@Component
public class UserDaoService {
  ...

  public User deleteById(int id){
      Iterator<User> iterator = users.iterator();
      while (iterator.hasNext()){
          User user = iterator.next();
          if(user.getId()==id){
              iterator.remove();
              return  user;
          }
      }
      return null;
  }
}
```
And in the **UserResource**:

```java
@RestController
public class UserResource {
    ...

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Object> deleteSingleUsers(@PathVariable int id){
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
        return ResponseEntity.ok().build();
        //return ResponseEntity.noContent().build();
    }
}
```

A deletion usually has a **200** OK response like in this case or a **204** No Content response.
