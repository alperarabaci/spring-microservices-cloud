package app.wordyourself.msscbrewery.service;

import app.wordyourself.msscbrewery.web.model.BeerDto;
import app.wordyourself.msscbrewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        BeerDto beer = BeerDto.builder().id(UUID.randomUUID()).beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.LAGER)
                .build();
        return beer;
    }
    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {

    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("Deleted" + beerId);
    }
}
