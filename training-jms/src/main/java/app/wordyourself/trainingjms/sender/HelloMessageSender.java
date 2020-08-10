package app.wordyourself.trainingjms.sender;

import app.wordyourself.trainingjms.config.JmsConfig;
import app.wordyourself.trainingjms.model.HelloWorldMessge;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.UUID;

/**
 * alper - 10/08/2020
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class HelloMessageSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.info("I'm sending a message.");
        HelloWorldMessge message = HelloWorldMessge.builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        log.info("Message send" + message.getId().toString());
    }

@Scheduled(fixedRate = 2000)
public void sendAndReceiveMessage() throws JMSException {
    log.info("I'm sending a message.");
    HelloWorldMessge message = HelloWorldMessge.builder()
            .id(UUID.randomUUID())
            .message("Hello!")
            .build();

    Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
        @Override
        public Message createMessage(Session session) throws JMSException {
            String messageText = null;
            try {
                messageText = objectMapper.writeValueAsString(message);
                Message helloMessage = session.createTextMessage(messageText);
                log.info("class: " + HelloWorldMessge.class.getCanonicalName());
                helloMessage.setStringProperty("_type", HelloWorldMessge.class.getCanonicalName());
                return helloMessage;
            } catch (JsonProcessingException e) {
                throw new JMSException(e.getMessage());
            }

        }
    });

    log.info("Message send" + receivedMessage.getBody((String.class)));
}
}
