package app.wordyourself.beerorderservice.services.testcomponets;

import app.wordyourself.beerorderservice.config.JmsConfig;
import app.wordyourself.mssc.model.BeerOrderDto;
import app.wordyourself.mssc.model.event.AllocateOrderResult;
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
public class AllocationListener {

    private final JmsTemplate jmsTemplate;

    /**
     * AllocateOrderAction send BeerOrderDto
     * Get and evaluate it: false, false for happy path
     * Send result to BeerOrderAllocationResultListener
     * @param msg
     */
    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(Message msg){

        BeerOrderDto request = (BeerOrderDto) msg.getPayload();

        boolean pendingInventory = false;
        boolean allocationError = false;
        boolean sendResponse = true;

        if (request.getCustomerRef() != null) {
            if (request.getCustomerRef().equals("fail-allocation")){
                allocationError = true;
            }  else if (request.getCustomerRef().equals("partial-allocation")) {
                pendingInventory = true;
            } else if (request.getCustomerRef().equals("dont-allocate")){
                sendResponse = false;
            }
        }

        final boolean finalPendingInventory = pendingInventory;
        request.getBeerOrderLines().forEach(beerOrderLineDto -> {

            if (finalPendingInventory) {
                beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity() - 1);
            } else {
                beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity());
            }
        });

        if (sendResponse) {
            AllocateOrderResult message = AllocateOrderResult.builder()
                    .beerOrderDto(request)
                    .allocationError(allocationError)
                    .pendingInventory(pendingInventory)
                    .build();

            jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, message);
        }
    }
}
