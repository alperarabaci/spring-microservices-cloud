package app.wordyourself.trainingjms;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingJmsApplication {

	public static void main(String[] args) throws Exception {

		ConfigurationImpl conf = new ConfigurationImpl()
				.setPersistenceEnabled(false)
				.setJournalDirectory("target/data/journal")
				.setSecurityEnabled(false)
				.addAcceptorConfiguration("inva", "vm://0");
		ActiveMQServer server = ActiveMQServers.newActiveMQServer(conf);
		SpringApplication.run(TrainingJmsApplication.class, args);
	}

}
