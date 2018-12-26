# Step 13 - Filtering

## Static Filtering

It is possible ignore a parameter in the output with the `@JsonIgnore` annotation:

```java
public class SomeBean {
    private String field1;
    private String field2;

    @JsonIgnore
    private String field3;

    ...
}
```

To ignore multiple filed it is possible annotate each filed with `@JsonIgnore` annotation or use `@JsonIgnoreProperties`:

```java
@JsonIgnoreProperties(
        value = {"field1", "field2"}
)
public class SomeBean {
    private String field1;
    private String field2;

    ...
}
```

In this case if a filed name change the `@JsonIgnoreProperties` annotation must bu updated, so the previous method is more secure.
