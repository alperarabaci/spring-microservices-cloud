package app.wordyourself.msscbrewery.service;

import app.wordyourself.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@Service
public class CustomerServiceImpl implements CustomerService{

    @Override
    public CustomerDto getById(UUID customerId) {
        CustomerDto customer = CustomerDto.builder().uuid(UUID.randomUUID())
                .name("alper")
                .build();
        return customer;
    }

}
