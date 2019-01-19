# Step 04 - Creating User Bean and User Service

## User

This is a simple example of a User class:

```java
public class User {
    private Integer id;
    private String name;
    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    // to String

    // getters and setters
}
```
---

## User Dao

This is a simple example of a User Dao class:

```java
@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Pinco", new Date()));
        users.add(new User(2, "Pallino", new Date()));
        users.add(new User(3, "Caio", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user:users){
            if(user.getId()==id){
                return  user;
            }
        }
        return null;
    }
}
```

At this moment a static list of users is used, after JPA will be used.

`@Component` tells Spring to detect the class for dependency injection.
