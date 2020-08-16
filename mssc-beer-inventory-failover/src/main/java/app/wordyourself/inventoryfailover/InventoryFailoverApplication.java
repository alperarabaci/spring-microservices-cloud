package app.wordyourself.inventoryfailover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -Dspring.profiles.active=local-discovery
 */
@SpringBootApplication
public class InventoryFailoverApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryFailoverApplication.class, args);
	}

}
