package app.wordyourself.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -Dspring.profiles.active=localmysql
 * -Dspring.profiles.active=local-discovery
 *
 */
@SpringBootApplication
public class BeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerServiceApplication.class, args);
	}

}
