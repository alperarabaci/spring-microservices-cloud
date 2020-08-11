package app.wordyourself.msscbeerservice.web.mapper;

import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.mssc.common.event.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * alper - 06/08/2020
 */
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto mapEntityToDto(Beer entity);

    BeerDto matEntityToDtoWithInventory(Beer beer);

    Beer mapDtoToEntity(BeerDto dto);

}
