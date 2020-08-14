package app.wordyourself.beerinventoryservice.listener;

import app.wordyourself.beerinventoryservice.config.JmsConfig;
import app.wordyourself.beerinventoryservice.service.AllocationService;
import app.wordyourself.mssc.common.event.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * alper - 14/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request){
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}