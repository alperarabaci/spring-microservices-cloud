package app.wordyourself.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -Dspring.profiles.active=google
 * -Dspring.profiles.active=local-discovery
 *
 * docker run -d -p 9411:9411 openzipkin/zipkin
 */
@SpringBootApplication
public class MsscBreweryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBreweryGatewayApplication.class, args);
	}

}
