package app.wordyourself.beerinventoryservice.service;

import app.wordyourself.mssc.model.BeerOrderDto;

/**
 * alper - 12/08/2020
 */
public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);

}
