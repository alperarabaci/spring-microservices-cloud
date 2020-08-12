package app.wordyourself.beerorderservice.sm.actions;

import app.wordyourself.beerorderservice.config.JmsConfig;
import app.wordyourself.beerorderservice.domain.BeerOrderEventEnum;
import app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum;
import app.wordyourself.beerorderservice.repositories.BeerOrderRepository;
import app.wordyourself.beerorderservice.services.BeerOrderManagerImpl;
import app.wordyourself.beerorderservice.web.mappers.BeerOrderMapper;
import app.wordyourself.mssc.model.BeerOrderDto;
import app.wordyourself.mssc.model.event.ValidateBeerOrderRequest;
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
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository repo;
    private final BeerOrderMapper mapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String headerIdText = (String) context.getMessageHeaders().getOrDefault(BeerOrderManagerImpl.ORDER_ID_HEADER, "");
        //Boyle yaziyormus millet ben her seyi service'e koyuyordum.
        UUID orderId = UUID.fromString(headerIdText);
        BeerOrderDto dto = mapper.beerOrderToDto(repo.getOne(orderId));

        sendMessage(dto);
    }


    public void sendMessage(BeerOrderDto beerOrderDto) {
        log.debug("I'm sending a message.");

        ValidateBeerOrderRequest message = ValidateBeerOrderRequest.builder()
                .id(UUID.randomUUID())
                .order(beerOrderDto)
                .build();

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER, message);

        log.info("Message send" + message.getId().toString());
    }
}