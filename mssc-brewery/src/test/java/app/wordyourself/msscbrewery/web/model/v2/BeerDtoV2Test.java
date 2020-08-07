package app.wordyourself.msscbrewery.web.model.v2;

import app.wordyourself.msscbrewery.web.model.BeerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@JsonTest
class BeerDtoV2Test extends BaseTest{

    @Test
    void testSeriliazeDto() throws JsonProcessingException {
        BeerDto dto = getDto();
        String jsonString = objectMapper.writeValueAsString(dto);
        log.info("testSeriliazeDto:      " +jsonString);
    }

    @Test
    void testSeriliazeExtendedDto() throws JsonProcessingException {
        BeerExtendedDto dto = BeerExtendedDto.childBuilder()
                .beerName("MyBeer")
                .beerStyle(BeerStyleEnum.ALE)
                .jsonPropertyExample("jsonPropTest")
                .build();
        String jsonString = objectMapper.writeValueAsString(dto);
        log.info("testSeriliazeDto:      " +jsonString);
    }

    @Test
    void testDeseriliaze() throws JsonProcessingException {
        String json = "{\"id\":\"fc30646d-d0cc-41bd-b1c3-b5538bdd8caf\",\"beerName\":\"MyBeer\",\"beerStyle\":\"LAGER\",\"upc\":10000000000001,\"createdDate\":\"2020-08-06T16:59:52.272388+03:00\",\"lastModifiedDate\":null}";
        BeerDto beer = objectMapper.readValue(json, BeerDto.class);

        log.info("testDeseriliaze: " + beer.toString());
    }

}