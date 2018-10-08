###Spring Note

---
####Annotations:
`@SpringBootApplication` is a convenience annotation that adds all of the following:  
>* `@Configuration` tags the class as a source of bean definitions for the application context.  
>* `@EnableAutoConfiguration` tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. Normally you would add `@EnableWebMvc` for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.  
>* `@ComponentScan` tells Spring to look for other components, configurations, and services in the hello package, allowing it to find the controllers.  


---
#####Developer Tools:
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

* Developer tools are automatically disabled when running a fully packaged application. If your
application is launched from java -jar or if it is started from a special classloader, then it is
considered a “production application”. 
* Flagging the dependency as optional in Maven or using compileOnly in Gradle is a best practice that prevents devtools from being transitively applied
to other modules that use your project.
* Applications that use spring-boot-devtools automatically restart whenever files on the classpath change. In IntelliJ IDEA, building the project (Build -> Build Project) trigers a restart.
* **Confusing:** As long as forking is enabled, you can also start your application by using the supported build plugins (Maven and Gradle), since DevTools needs an isolated application classloader to operate properly. By default, Gradle and Maven do that when they detect DevTools on the classpath.
---
####Command cheat-sheet
1. Package and execute jar:  
`mvn package -DSkipTests && java -jar target/laboratory-springboot-quickstart-0.0.1-SNAPSHOT.jar`
2. 




