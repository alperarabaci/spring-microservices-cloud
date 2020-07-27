package app.wordyourself.msscbrewery.service;

import app.wordyourself.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        BeerDto beer = BeerDto.builder().id(UUID.randomUUID()).beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
        return beer;
    }
}
