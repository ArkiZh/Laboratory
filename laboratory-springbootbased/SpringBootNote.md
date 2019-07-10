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

>* Developer tools are automatically disabled when running a fully packaged application. If your
application is launched from java -jar or if it is started from a special classloader, then it is
considered a “production application”. 
>* Flagging the dependency as optional in Maven or using compileOnly in Gradle is a best practice that prevents devtools from being transitively applied
to other modules that use your project.
>* Applications that use spring-boot-devtools automatically restart whenever files on the classpath change. In IntelliJ IDEA, building the project (Build -> Build Project) trigers a restart.
>* **Confusing:** As long as forking is enabled, you can also start your application by using the supported build plugins (Maven and Gradle), since DevTools needs an isolated application classloader to operate properly. By default, Gradle and Maven do that when they detect DevTools on the classpath.
>* Disable to show the changes to your application’s auto-configuration as you make changes such as adding or removing beans and setting configuration properties.  
  `spring.devtools.restart.log-condition-evaluation-delta=false`
>* Excluding Resources: `spring.devtools.restart.exclude=static/**,public/**`
>* Keep defaults and add additional exclusions: `spring.devtools.restart.additional-exclude`
>* Watching Additional Paths: `spring.devtools.restart.additionalpaths`
>* Disabling Restart: `spring.devtools.restart.enabled`
>* Using a Trigger File: `spring.devtools.restart.trigger-file`. 
>* Customizing the Restart Classloader:
         
>       restart.exclude.companycommonlibs=/mycorp-common-[\\w-]+\.jar
>       restart.include.projectcommon=/mycorp-myproj-[\\w-]+\.jar
>* Global Settings: adding a file named `.spring-bootdevtools.properties` to your `$HOME` folder      

---
####Customize configuration
1. Customizing the Banner:   
The banner that is printed on start up can be changed by adding a `banner.txt` file to your classpath
or by setting the `spring.banner.location` property to the location of such a file. If the file has
an encoding other than `UTF-8`, you can set `spring.banner.charset`. In addition to a text file, you
can also add a `banner.gif`, `banner.jpg`, or `banner.png` image file to your classpath or set the
`spring.banner.image.location` property. Images are converted into an ASCII art representation
and printed above any text banner.
2. 
        
---
####Command cheat-sheet
1. Package and execute jar:  
`mvn package -DSkipTests && java -jar target/laboratory-springboot-quickstart-0.0.1-SNAPSHOT.jar`
2. Enable debug log:  
`java -jar myproject-0.0.1-SNAPSHOT.jar --debug`




