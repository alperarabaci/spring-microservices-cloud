package app.wordyourself.beerorderservice.web.mappers;

import app.wordyourself.beerorderservice.domain.Customer;
import app.wordyourself.mssc.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 * alper - 14/08/2020
 */
@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer dto);
}