package app.wordyourself.msscbeerservice.web.mapper;

import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * alper - 06/08/2020
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto mapEntityToDto(Beer entity);

    Beer mapDtoToEntity(BeerDto dto);

}
