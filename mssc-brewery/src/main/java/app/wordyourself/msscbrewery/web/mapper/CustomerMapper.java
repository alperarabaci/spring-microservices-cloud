package app.wordyourself.msscbrewery.web.mapper;

import app.wordyourself.msscbrewery.domain.Beer;
import app.wordyourself.msscbrewery.domain.Customer;
import app.wordyourself.msscbrewery.web.model.BeerDto;
import app.wordyourself.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 * alper - 06/08/2020
 */
@Mapper
public interface CustomerMapper {

    CustomerDto mapEntityToDto(Customer entity);

    Customer mapDtoToEntity(CustomerDto dto);
}
