package app.wordyourself.beerorderservice.services;

import app.wordyourself.beerorderservice.domain.BeerOrder;
import app.wordyourself.beerorderservice.domain.BeerOrderEventEnum;
import app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum;
import org.springframework.statemachine.StateMachine;

import java.util.UUID;

/**
 * alper - 12/08/2020
 */
public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> validated(BeerOrder beerOrder);

    void processValidationResult(UUID orderId, Boolean isValid);
}
