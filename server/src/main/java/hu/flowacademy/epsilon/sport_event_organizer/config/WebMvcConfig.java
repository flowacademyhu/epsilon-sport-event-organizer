package hu.flowacademy.epsilon.sport_event_organizer.config;

import hu.flowacademy.epsilon.sport_event_organizer.utils.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  private final long MAX_AGE_SECS = 3600;

  @Autowired
private RequestInterceptor requestInterceptor;
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(MAX_AGE_SECS);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(requestInterceptor).addPathPatterns("/auth/**");
  }
}