# Step 01 - Basic Spring Boot Web Application Setup

## Spring Initializer

[Spring Initializer](https://start.spring.io/) helps to start new applications easily.

Params:
- **File generations** - is the type of build tool.
- **Spring Boot Version** - the version to use.
- **Group** - the project package.
- **Artifact** - the name of the application.
- **Dependencies** - the list of Spring Boot Starters and Dependencies to add to the project.
  Some examples are:
  - **Web** - contains a lot of dependencies to build web applications.
  - **DevTools** - contains some tools to develop web applications.

**Generate Project** will download a zip file with a maven project to import to the IDE.

---

In the video course is used version 1.4.3,
I have to use version 2.1.0 so I'll annotate the differences from the two versions during the exercises.

---

## Import project into IntelliJ IDEA
1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish
5. `Run` > `Edit Configurations`
6. on `maven` > `Command Lined` write: `lagom:runAll`
7. `build` > `Build Project`
8. now you can run it

---

## pom.xml

- **\<groupId\>** - the application package.
- **\<artifactId\>** - the application id name.
- **\<version\>** - the current version of the application.
- **\<packaging\>** - the type extension used by the application. Spring Boot uses a `jar` instead of `war` that is common for java web applications.
- **\<name\>**, **\<description\>** - basic application informations:
- **\<parent\>** - the pom inheritance.
  - **\<groupId\>** - the parent package.
  - **\<artifactId\>** - the parent id name.
  - **\<version\>** - the parent version.
- **\<dependencies\>** - list of dependencies included in the project:
    - **\<dependency\>** - a single dependency description:
      - **\<groupId\>** - dependency's package.
      - **\<artifactId\>** - dependency's id name.
      - **\<scope\>** - dependency's scope.
- **\<build\>** - build informations:
  - **\<plugins\>** - list of plugins that help to run the application:
    - **\<plugin\>** - single plugin description:
      - **\<groupId\>** - plugin's package.
      - **\<artifactId\>** - plugin's id name.
- **\<properties\>** - project properties:
  - **\<java.version\>** - the java version used.



### Dependencies

- **org.springframework.boot**
  - **spring-boot-starter-web** - web dependencies.
  - **spring-boot-devtools** - dev tools dependencies.
  - **spring-boot-starter-test** - test tools dependencies.


### Plugins
- **org.springframework.boot**
  - **spring-boot-maven-plugin** - collection of basic useful plugins.

---

## SpringBootFirstWebApplication.java

```java
@SpringBootApplication
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}
}
```

```java
@SpringBootApplication
```
This annotation initializes:
- Spring (with a component scan)
- Spring Boot (with auto Configuration)

```java
SpringApplication.run
```

Runs a specific Spring Boot application annotated with `@SpringBootApplication`

---

## application.properties
Is the application Configuration file used to set up the variables.
