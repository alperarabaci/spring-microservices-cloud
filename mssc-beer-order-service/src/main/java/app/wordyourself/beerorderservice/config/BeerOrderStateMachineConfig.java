package app.wordyourself.beerorderservice.config;

import app.wordyourself.beerorderservice.domain.BeerOrderEventEnum;
import app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum;
import app.wordyourself.beerorderservice.repositories.BeerOrderRepository;
import app.wordyourself.beerorderservice.services.BeerOrderManagerImpl;
import app.wordyourself.beerorderservice.web.mappers.BeerOrderMapper;
import app.wordyourself.mssc.model.BeerOrderDto;
import app.wordyourself.mssc.model.ValidateBeerOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;
import java.util.UUID;

/**
 * alper - 12/08/2020
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableStateMachineFactory
public class BeerOrderStateMachineConfig extends StateMachineConfigurerAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository repo;
    private final BeerOrderMapper mapper;

    @Override
    public void configure(StateMachineTransitionConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(BeerOrderStatusEnum.NEW)
                .target(BeerOrderStatusEnum.VALIDATION_PENDING)
                .event(BeerOrderEventEnum.VALIDATE_ORDER)
                .action(validateOrder())
                .and()
                .withExternal()
                .source(BeerOrderStatusEnum.NEW)
                .target(BeerOrderStatusEnum.VALIDATED)
                .event(BeerOrderEventEnum.VALIDATION_PASSED)
                .and()
                .withExternal()
                .source(BeerOrderStatusEnum.NEW)
                .target(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                .event(BeerOrderEventEnum.VALIDATION_FAILED);
    }

    private Action<BeerOrderStatusEnum, BeerOrderEventEnum> validateOrder() {
        return context -> {
            String headerIdText = (String) context.getMessageHeaders().getOrDefault(BeerOrderManagerImpl.ORDER_ID_HEADER, "");
            //TODO move to service
            UUID orderId = UUID.fromString(headerIdText);
            BeerOrderDto dto = mapper.beerOrderToDto(repo.getOne(orderId));

            sendMessage(dto);
        };
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

    @Override
    public void configure(StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(BeerOrderStatusEnum.NEW)
                .states(EnumSet.allOf(BeerOrderStatusEnum.class))
                .end(BeerOrderStatusEnum.PICKED_UP)
                .end(BeerOrderStatusEnum.DELIVERED)
                .end(BeerOrderStatusEnum.DELIVERY_EXCEPTION)
                .end(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                .end(BeerOrderStatusEnum.ALLOCATION_EXCEPTION);
    }
}