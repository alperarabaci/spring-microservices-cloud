package app.wordyourself.msscbeerservice.service;

import app.wordyourself.msscbeerservice.web.model.BeerDto;
import app.wordyourself.msscbeerservice.web.model.BeerPagedList;
import app.wordyourself.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * alper - 06/08/2020
 */
public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto save(BeerDto beerDto);

    BeerDto update(UUID beerId, BeerDto beerDto);

    BeerDto getBeerByUpc(String upc, Boolean showInventoryOnHand);
}
