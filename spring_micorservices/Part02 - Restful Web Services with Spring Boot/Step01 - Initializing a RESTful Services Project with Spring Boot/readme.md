# Step 01 - Initializing a RESTful Services Project with Spring Boot

## Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.1`
- Group `com.lordalucard90.springboottutorials`
- Artifact `restful-web-services`
- Dependencies:
  - **Web** - contains a lot of dependencies to build web applications.
  - **DevTools** - contains some tools to develop web applications.
  - **JPA** - contains Java Persistence API.
  - **H2** - contains H2 Database.

---

## Import project into IntelliJ IDEA

1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish
5. `Run` > `Edit Configurations`
7. `build` > `Build Project`
8. now you can run it

---

## Project Structure

```
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── lordalucard90
    │   │           └── springboottutorials
    │   │               └── restfulwebservices
    │   │                   └── RestfulWebServicesApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── lordalucard90
                   └── springboottutorials
                        └── restfulwebservices
                            └── RestfulWebServicesApplicationTests.java
```

**pom.xml** contains the information about the current atifact, the pom parent, the dependencies and the java version.

**src/main/java** contains the source code.

**src/main/resources/application.properties** contains the application configurations.

**src/test** contains the tests.
