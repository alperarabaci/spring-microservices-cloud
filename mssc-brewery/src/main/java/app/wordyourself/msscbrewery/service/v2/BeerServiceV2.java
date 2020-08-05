package app.wordyourself.msscbrewery.service.v2;

import app.wordyourself.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
public interface BeerServiceV2 {

    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);

    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteBeer(UUID beerId);

}
