package app.wordyourself.msscbeerservice.web.mapper;
import app.wordyourself.msscbeerservice.domain.Customer;
import app.wordyourself.msscbeerservice.web.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 * alper - 06/08/2020
 */
@Mapper
public interface CustomerMapper {

    CustomerDto mapEntityToDto(Customer entity);

    Customer mapDtoToEntity(CustomerDto dto);
}
