# Step 03 - Demystifying some of the Spring Boot magic

## spring-boot-starter-parent

With `ctrl + click` on **\<parent\> \<version\>** is possible to navigate to the parent xml file which pom is extending. With another `ctrl + click` on **\<parent\> \<version\>** is possible to navigate from this parent to the base xml. In the properties of this xml is possible to see all the components used and their versions.

The **spring-boot-starter-parent** has the default configurations of all the important maven plugins.

The properties written in the **pom.xml** overrides the default configurations written into **spring-boot-starter-parent** xml.

## spring-boot-starter-web

Includes all the dependencies needed to run web applications like spring-web, spring-webmvc, validations, logging and so on.

One of the important dependencies is **tomcat-embedded**, this dependency allow to configure and run automatically the application on **Tomcat Web Server**.


## spring-boot-devtools

Allows some useful functionalities like live-restart during code creation and modification thanks to a continuous monitoring of code changes.

Only the dynamic part is reloaded, the static part like dependencies is not automatically reloaded since the reload of the dynamic part is much quickly the the reload of the static one.
