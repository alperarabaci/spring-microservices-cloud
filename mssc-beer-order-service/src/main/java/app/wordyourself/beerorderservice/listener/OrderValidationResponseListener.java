package app.wordyourself.beerorderservice.listener;

import app.wordyourself.beerorderservice.config.JmsConfig;
import app.wordyourself.beerorderservice.services.BeerOrderManager;
import app.wordyourself.mssc.model.event.ValidateOrderResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * alper - 12/08/2020
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderValidationResponseListener {

    private final BeerOrderManager manager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listenForHello(@Payload ValidateOrderResultMessage request,
                               @Headers MessageHeaders headers,
                               Message message) throws JMSException {
        log.debug("I got a message");

        manager.processValidationResult(request.getOrderId(), request.getIsValid());

    }
}
