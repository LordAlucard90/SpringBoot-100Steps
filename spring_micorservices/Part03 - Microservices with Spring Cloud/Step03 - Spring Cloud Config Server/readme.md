# Step 03 - Spring Cloud Config Server

## Initialization

Generate Spring Boot Initial project from [Spring Initializr](https://start.spring.io/), with:
- Generate a `Maven Project` with `Java` and Spring Boot `2.1.1`
- Group `com.lordalucard90.microservices`
- Artifact `config-server`
- Dependencies:
  - **DevTools** - contains some tools to develop web applications.
  - **Config Server** - contains the Cloud Config Server.

---

## Import project into IntelliJ IDEA

1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish
7. `build` > `Build Project`
8. now you can run it

---

## Enable spring-boot-devtools livereload on IntelliJ IDEA

1. `File` > `Setting` > `Build, Execution, Deployment` > `Compiler`
2. check `Build project automatically`
3. `Help` > `Find Action..`
4. search `registry`
5. check `compiler.automake.allow.when.app.running`

---

## Basic Configuration

It is important to set the server port different form the previous service in the **application.properties** file:

```
spring.application.name=config-server
server.port=8888
```

---

## Git Repository

#### Git Installation

It is necessary install [Git](https://git-scm.com/) to be able to create a local git repository that will be used by Spring Cloud Config Server.

It is possible to learn git with a online console: http://try.github.io/.

Configure git user:

```bash
$ git config --global user.name "Your Name"
$ git config --global user.email "you@example.com"
```

#### Repository Creation

```bash
$ mkdir git-localconfig-repo
$ cd git-localconfig-repo/
$ git init
```

If the folder is created into the project folder is not necessary import the new folder into the project resources.

#### Resources Creation

Create a file in the `git-localconfig-repo` named **limits-service.properties**:

```
limit-service.minimum=8
limit-service.maximum=888
```

#### Add The New File To Git

```bash
$ git add limits-service.properties
# or
$ git add -A # add all
$ git commit -m "limits-service.properties created"
```

#### Set Up The Configuration

It is necessary to link the repository to Spring with the **application.properties** file:

```
spring.cloud.config.server.git.uri=file:///.../git-localconfig-repo/
```
And it is also necessary to enable the server:

```java
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
```

Now it is possible to retrieve the `limits-service` default configuration at:

http://localhost:8888/limits-service/default

The response is :

```json
{
  "name":"limits-service",
  "profiles":["default"],
  "label":null,
  "version":"4439f3953c4af55c2e6bd89e906e2dd7b92e7542",
  "state":null,
  "propertySources":[
    {
      "name":"file:///.../git-localconfig-repo//limits-service.properties",
      "source":{
        "mit-service.minimum":"8",
        "limit-service.maximum":"888"
      }
    }
  ]
}
```
Where:
- **name** - is the configuration name.
- **profiles** - is the profile retrieved.
- **version** - is the commit id.
- **propertySources** - are the properties retrieved.
- **propertySources/name** - is the file path.
- **propertySources/source** - are the properties on limits-service.properties file.

---

## Setting Up Different Environments

It is possible create different environments by creating new files on the git repository like:

- **limits-service-`dev`.properties**
- **limits-service-`qa`.properties**

It is now possible change the default values:

```
# dev
limit-service.minimum=1
limit-service.maximum=111
# qa
limit-service.minimum=2
limit-service.maximum=222
```

The new files are added to the server after a commit:

```bash
$ git add -A # add all
$ git commit -m "limits-service dev and qa created"
```

The new configurations are available at the urls:

- **dev** - http://localhost:8888/limits-service/dev
- **qa** - http://localhost:8888/limits-service/qa

For example the **dev** response is:

```json
{
  "name":"limits-service",
  "profiles":["dev"],
  "label":null,
  "version":"192033d0f6efbdbd30d9389361aa0969eab144ef",
  "state":null,
  "propertySources":[
    {
      "name":"file:///.../git-localconfig-repo//limits-service-dev.properties",
      "source":{
        "limit-service.minimum":"1"
      }
    },
    {
      "name":"file:///.../git-localconfig-repo//limits-service.properties",
      "source":{
        "limit-service.minimum":"8",
        "limit-service.maximum":"888"}
      }
  ]
}
```

The `sources` are presented by priority: first the current asked then the default configuration.

If a value is not overwritten by the current configuration the default value is used.

---

## Connect Service to Config Server

To make able the `limist-service` to retrieve the parameters from the Config Server **application.properties** hat to be renamed with **bootstrap.properties** with these parameters:

```
# Service And Configuration Name
spring.application.name = limits-service
# Config Server Url
spring.cloud.config.uri = http://localhost:8888
```

Now it is possible to go to http://localhost:8080/limits and see the default parameters defined in **limits-service.properties**

#### Retrieving Different Environment

It is possible to set a specific environment by specifying it in the **bootstrap.properties** file:

```
spring.profiles.active = dev
```
