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
    public void listen(Message msg){
        boolean isValid = true;
        boolean sendResponse = true;

        ValidateBeerOrderRequest request = (ValidateBeerOrderRequest) msg.getPayload();

        log.debug("########### I RAN ########");

        if (request.getOrder().getCustomerRef() != null) {
            if (request.getOrder().getCustomerRef().equals("fail-validation")){
                isValid = false;
            } else if (request.getOrder().getCustomerRef().equals("dont-validate")){
                sendResponse = false;
            }
        }
        if (sendResponse) {
            ValidateOrderResultMessage message = ValidateOrderResultMessage.builder()
                    .isValid(isValid)
                    .orderId(request.getOrder().getId())
                    .build();

            jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,message);
        }
    }
}