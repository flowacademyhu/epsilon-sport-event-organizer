package hu.flowacademy.epsilon.sporteventorganizer;

import hu.flowacademy.epsilon.sporteventorganizer.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SportEventOrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportEventOrganizerApplication.class, args);
    }

}
