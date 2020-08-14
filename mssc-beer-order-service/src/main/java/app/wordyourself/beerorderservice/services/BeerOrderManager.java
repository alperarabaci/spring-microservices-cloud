package app.wordyourself.beerorderservice.services;

import app.wordyourself.beerorderservice.domain.BeerOrder;
import app.wordyourself.beerorderservice.domain.BeerOrderEventEnum;
import app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum;
import app.wordyourself.mssc.model.BeerOrderDto;
import org.springframework.statemachine.StateMachine;

import java.util.UUID;

/**
 * alper - 12/08/2020
 */
public interface BeerOrderManager {

    StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> validated(BeerOrder beerOrder);

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);

    void beerOrderAllocationPassed(BeerOrderDto beerOrder);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrder);

    void beerOrderAllocationFailed(BeerOrderDto beerOrder);

    void beerOrderPickedUp(UUID id);

    void cancelOrder(UUID id);
}
