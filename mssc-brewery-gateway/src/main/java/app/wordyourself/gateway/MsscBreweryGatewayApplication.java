package app.wordyourself.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -Dspring.profiles.active=google
 */
@SpringBootApplication
public class MsscBreweryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBreweryGatewayApplication.class, args);
	}

}