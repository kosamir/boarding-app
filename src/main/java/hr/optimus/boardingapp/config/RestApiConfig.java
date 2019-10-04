package hr.optimus.boardingapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource({ "classpath:application.properties" })
@ConfigurationProperties(prefix = "microblink")

@Data
public class RestApiConfig {

	private String apiKey;
	private String apiSecret;
	
	private String uploadDir;
	private String apiUrl;
}
