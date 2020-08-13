package app.wordyourself.beerorderservice.services.testcomponets;

import app.wordyourself.beerorderservice.config.JmsConfig;
import app.wordyourself.mssc.model.event.ValidateBeerOrderRequest;
import app.wordyourself.mssc.model.event.ValidateOrderResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * alper - 13/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void list(Message msg){

        ValidateBeerOrderRequest request = (ValidateBeerOrderRequest) msg.getPayload();

        log.debug("########### I RAN ########");

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResultMessage.builder()
                        .isValid(true)
                        .orderId(request.getOrder().getId())
                        .build());

    }
}