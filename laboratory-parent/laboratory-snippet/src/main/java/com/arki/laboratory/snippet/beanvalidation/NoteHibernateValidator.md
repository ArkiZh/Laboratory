From https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/  

**Requires:**
+ A JDK 8
+ Apache Maven
+ An Internet connection (Maven has to download all required libraries)

**Dependencies** 
`
<dependency>
    <groupId>org.hibernate.validator</groupId>  
    <artifactId>hibernate-validator</artifactId>
    <version>6.0.13.Final</version>
</dependency>`

Hibernate Validator requires an implementation of the Unified Expression Language (JSR 341) for evaluating dynamic expressions in constraint violation messages (see Section 4.1, “Default message interpolation”):
<dependency>
    <groupId>org.glassfish</groupId>
    <artifactId>javax.el</artifactId>
    <version>3.0.1-b09</version>
</dependency>

Hibernate Validator requires an implementation of the Unified Expression Language (JSR 341) for evaluating dynamic expressions in constraint violation messages (see Section 4.1, “Default message interpolation”).
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator-cdi</artifactId>
    <version>6.0.13.Final</version>
</dependency>



