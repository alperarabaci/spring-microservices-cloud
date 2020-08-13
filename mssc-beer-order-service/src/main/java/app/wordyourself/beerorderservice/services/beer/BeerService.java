package app.wordyourself.beerorderservice.services.beer;

import app.wordyourself.mssc.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

/**
 * alper - 13/08/2020
 */
public interface BeerService {
    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}
