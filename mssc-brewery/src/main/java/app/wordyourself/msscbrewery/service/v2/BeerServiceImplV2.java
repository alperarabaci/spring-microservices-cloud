package app.wordyourself.msscbrewery.service.v2;

import app.wordyourself.msscbrewery.web.model.v2.BeerDtoV2;
import app.wordyourself.msscbrewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@Slf4j
@Service
public class BeerServiceImplV2 implements  BeerServiceV2{

    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        BeerDtoV2 beer = BeerDtoV2.builder().id(UUID.randomUUID()).beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.LAGER)
                .build();
        return beer;
    }
    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {

    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("Deleted" + beerId);
    }
}
