package app.wordyourself.msscbrewery.web.controller;

import app.wordyourself.msscbrewery.service.CustomerService;
import app.wordyourself.msscbrewery.web.model.BeerDto;
import app.wordyourself.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        CustomerDto customer = customerService.getById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
