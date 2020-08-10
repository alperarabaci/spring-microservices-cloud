package app.wordyourself.trainingjms.listener;

import app.wordyourself.trainingjms.config.JmsConfig;
import app.wordyourself.trainingjms.model.HelloWorldMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * alper - 10/08/2020
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessge hwMessage,
                       @Headers MessageHeaders headers,
                       Message message) {
        log.info("I got a message");
        log.info(hwMessage.toString());
    }
    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHello(@Payload HelloWorldMessge hwMessage,
                       @Headers MessageHeaders headers,
                       Message message) throws JMSException {
        log.info("I got a message and answer");

        HelloWorldMessge payloadMessage = HelloWorldMessge.builder()
                .id(UUID.randomUUID())
                .message("Hello!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);
    }

}
