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
