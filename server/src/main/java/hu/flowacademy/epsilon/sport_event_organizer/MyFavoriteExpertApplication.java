package hu.flowacademy.epsilon.sport_event_organizer;

import hu.flowacademy.epsilon.sport_event_organizer.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MyFavoriteExpertApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFavoriteExpertApplication.class, args);
	}

}
