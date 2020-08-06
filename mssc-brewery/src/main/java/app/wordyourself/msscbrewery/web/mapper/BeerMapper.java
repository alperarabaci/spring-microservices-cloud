package app.wordyourself.msscbrewery.web.mapper;

import app.wordyourself.msscbrewery.domain.Beer;
import app.wordyourself.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * alper - 06/08/2020
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto mapEntityToDto(Beer entity);

    Beer mapDtoToEntity(BeerDto dto);

}
