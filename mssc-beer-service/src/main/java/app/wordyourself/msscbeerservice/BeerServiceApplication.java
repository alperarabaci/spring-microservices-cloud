package app.wordyourself.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * -Dspring.profiles.active=localmysql
 * -Dspring.profiles.active=local-discovery
 *
 */
@EnableFeignClients
@SpringBootApplication
public class BeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerServiceApplication.class, args);
	}

}
