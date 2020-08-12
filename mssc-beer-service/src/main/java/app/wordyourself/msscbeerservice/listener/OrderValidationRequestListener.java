package app.wordyourself.msscbeerservice.listener;

import app.wordyourself.mssc.model.BeerOrderLineDto;
import app.wordyourself.mssc.model.event.ValidateBeerOrderRequest;
import app.wordyourself.mssc.model.event.ValidateOrderResultMessage;
import app.wordyourself.msscbeerservice.config.JmsConfig;
import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;
import java.util.Optional;

/**
 * alper - 12/08/2020
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderValidationRequestListener {

    private final JmsTemplate jmsTemplate;
    private final BeerRepository repo;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER)
    public void listenForHello(@Payload ValidateBeerOrderRequest request,
                               @Headers MessageHeaders headers,
                               Message message) throws JMSException {
        log.debug("I got a message");

        List<BeerOrderLineDto> lines = request.getOrder().getBeerOrderLines();
        boolean isValid = true;
        for (BeerOrderLineDto line: lines) {
            Optional<Beer> beer = repo.findByUpc(line.getUpc());
            if(!beer.isPresent()){
                isValid = false;
                break;
            }
        }

        ValidateOrderResultMessage payloadMessage = ValidateOrderResultMessage.builder()
                .orderId(request.getOrder().getId())
                .isValid(isValid)
                .build();

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT, payloadMessage);
    }
}
