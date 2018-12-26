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

---

## Dynamic Filtering

It is possible generate a dynamic filter through `MappingJacksonValue` and `FilterProvider` classes:

```java
@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }

    ...
}
```

In this case is important declare the filter:

```java
@JsonFilter("SomeBeanFilter")
public class SomeBean { ... }
```
