package app.wordyourself.msscbeerservice.service;

import app.wordyourself.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * alper - 06/08/2020
 */
public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto save(BeerDto beerDto);

    BeerDto update(UUID beerId, BeerDto beerDto);
}
