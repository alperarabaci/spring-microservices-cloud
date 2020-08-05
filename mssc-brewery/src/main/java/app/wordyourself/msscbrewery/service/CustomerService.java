package app.wordyourself.msscbrewery.service;

import app.wordyourself.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
public interface CustomerService {

    CustomerDto getById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID beerId, CustomerDto dto);

    void deleteCustomer(UUID id);
}
