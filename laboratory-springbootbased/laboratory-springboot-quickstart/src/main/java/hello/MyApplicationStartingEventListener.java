package hello;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {


    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("=============== MyApplicationStartingEventListener");
    }
}
