package app.wordyourself.beerorderservice.sm.actions;

import app.wordyourself.beerorderservice.config.JmsConfig;
import app.wordyourself.beerorderservice.domain.BeerOrderEventEnum;
import app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum;
import app.wordyourself.beerorderservice.services.BeerOrderManagerImpl;
import app.wordyourself.mssc.model.event.AllocationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * alper - 14/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);

        AllocationFailureEvent message = AllocationFailureEvent.builder()
                .orderId(UUID.fromString(beerOrderId))
                .build();

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_FAILURE_QUEUE, message);

        log.debug("Sent Allocation Failure Message to queue for order id " + beerOrderId);
    }
}