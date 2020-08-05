package app.wordyourself.msscbrewery.service;

import app.wordyourself.msscbrewery.web.model.BeerDto;
import app.wordyourself.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Override
    public CustomerDto getById(UUID customerId) {
        CustomerDto customer = CustomerDto.builder().id(UUID.randomUUID())
                .name("alper")
                .build();
        return customer;
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID beerId, CustomerDto dto) {

    }

    @Override
    public void deleteCustomer(UUID id) {
        log.debug("Deleted" + id);
    }

}
