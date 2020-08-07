package app.wordyourself.msscbrewery.web.model.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * alper - 06/08/2020
 */
@Data

public class BeerExtendedDto extends BeerDtoV2 {
@JsonProperty("Jsoncan")
String jsonPropertyExample;

    public BeerExtendedDto(String jsonPropertyExample) {
        this.jsonPropertyExample = jsonPropertyExample;
    }

    /**
     * https://www.baeldung.com/lombok-builder-inheritance
     * @param jsonPropertyExample
     */
    @Builder(builderMethodName = "childBuilder")
    public BeerExtendedDto(@Null UUID id, @NotBlank String beerName, @NotNull BeerStyleEnum beerStyle, @Positive Long upc, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String jsonPropertyExample) {
        super(id, beerName, beerStyle, upc, createdDate, lastModifiedDate);
        this.jsonPropertyExample = jsonPropertyExample;
    }
}
