package app.wordyourself.msscbrewery.web.model.v2;

import app.wordyourself.msscbrewery.web.model.BeerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * alper - 06/08/2020
 */
public class BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    public BeerDto getDto(){
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("MyBeer")
                .upc(10000000000001L)
                .beerStyle(BeerStyleEnum.LAGER)
                .createdDate(OffsetDateTime.now())
                .build();
    }

}
