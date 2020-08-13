package app.wordyourself.beerorderservice.sm;

import app.wordyourself.beerorderservice.domain.BeerOrder;
import app.wordyourself.beerorderservice.domain.BeerOrderEventEnum;
import app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum;
import app.wordyourself.beerorderservice.repositories.BeerOrderRepository;
import app.wordyourself.beerorderservice.services.BeerOrderManagerImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * alper - 12/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderStateChangeInterceptor extends StateMachineInterceptorAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository orderRepository;

    @Transactional
    @Override
    public void preStateChange(State<BeerOrderStatusEnum, BeerOrderEventEnum> state,
                               Message<BeerOrderEventEnum> message,
                               Transition<BeerOrderStatusEnum, BeerOrderEventEnum> transition,
                               StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachine) {

        if(message==null){
            return;
        }
        String headerIdText = (String) message.getHeaders().getOrDefault(BeerOrderManagerImpl.ORDER_ID_HEADER, "");
        if(!StringUtils.hasText(headerIdText)){
            return;
        }
        UUID orderId = UUID.fromString(headerIdText.toString());
        BeerOrder order = orderRepository.findById(orderId).get();
        order.setOrderStatus(state.getId());
        orderRepository.save(order);

        log.debug("STATUS INCEPTION:" +state.getId() + " " + order.getId());
    }
}