package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);

        /*Listeners:
        *ApplicationStartingEvent
        *ApplicationEnvironmentPreparedEvent
        *ApplicationPreparedEvent
        *ApplicationStartedEvent
        *ApplicationReadyEvent
        *ApplicationFailedEvent
        */
        springApplication.addListeners(new MyApplicationStartingEventListener(),
                new MyApplicationEnvironmentPreparedEventListener());
        springApplication.run(args);
        //SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("=============== Command line runner!");
            /*//Print beans:
            System.out.println("=============== Let's inspect the beans provided by spring boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String s : beanNames) {
                System.out.println("=============== " + s);
            }*/
        };
    }
}
