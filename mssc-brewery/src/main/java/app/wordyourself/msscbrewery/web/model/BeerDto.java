package app.wordyourself.msscbrewery.web.model;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

import app.wordyourself.msscbrewery.web.model.v2.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
	@Null
	private UUID id;
	@NotBlank
	private String beerName;
	@NotBlank
	private BeerStyleEnum beerStyle;
	@Positive
	private Long upc;

	OffsetDateTime createdDate;
	OffsetDateTime lastModifiedDate;
}
