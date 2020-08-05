package app.wordyourself.msscbeerservice.web.controller;

import app.wordyourself.msscbeerservice.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        CustomerDto customer = CustomerDto.builder()
                .name("alper")
                .id(UUID.randomUUID())
                .build();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody CustomerDto customerDto) {
        CustomerDto savedDto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("alper")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http:localhost:8080/api/v1/customer/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("customerId}")
    public ResponseEntity handleUpdate(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {


        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable UUID customerId) {
        //customerService.deleteCustomer(customerId);
    }

}
