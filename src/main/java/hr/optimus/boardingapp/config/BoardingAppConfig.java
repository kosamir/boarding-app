package hr.optimus.boardingapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource({ "classpath:application.properties" })
@ConfigurationProperties(prefix = "boardingapp")

@Data
public class BoardingAppConfig {
	private String webhost;

}
