package app.wordyourself.beerorderservice.sm.actions;

import app.wordyourself.beerorderservice.config.JmsConfig;
import app.wordyourself.beerorderservice.domain.BeerOrder;
import app.wordyourself.beerorderservice.domain.BeerOrderEventEnum;
import app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum;
import app.wordyourself.beerorderservice.repositories.BeerOrderRepository;
import app.wordyourself.beerorderservice.services.BeerOrderManagerImpl;
import app.wordyourself.beerorderservice.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * alper - 12/08/2020
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String id = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        UUID beerOrderId = UUID.fromString(id);
        BeerOrder beerOrder = beerOrderRepository.findOneById(beerOrderId);

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE,  beerOrderMapper.beerOrderToDto(beerOrder));

        log.debug("Sent Allocation Request for order id: " + beerOrderId);
    }
}