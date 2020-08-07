package app.wordyourself.msscbrewery.web.model.v2;

import app.wordyourself.msscbrewery.web.model.BeerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * alper - 06/08/2020
 */

@ActiveProfiles("kebap")
@JsonTest
public class BeerDtoV2KebapTest extends BaseTest {

    @Test
    void testKebap() throws JsonProcessingException {
        BeerDto dto = getDto();

        String json = objectMapper.writeValueAsString(dto);

        System.out.println(json);
    }
}