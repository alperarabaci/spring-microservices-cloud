package app.wordyourself.msscbreweryclient.web.client;

import app.wordyourself.msscbreweryclient.web.model.BeerDto;
import app.wordyourself.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Slf4j
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
        BeerDto beer = client.getBeerById(UUID.randomUUID());
        assertThat(beer.getId()).isNotNull();
    }

    @Test
    void testSaveNewBeer() {
        BeerDto beer = BeerDto.builder().beerName("myBeer")
                .price(BigDecimal.ONE)
                .build();
        URI uri = client.saveNewBeer(beer);
        assertNotNull(uri);

        log.info("URI:" + uri.toString());
    }

    void testUpdateBeer() {
        BeerDto beer = BeerDto.builder()
                .beerName("myBeer")
                .price(BigDecimal.ONE)
                .id(UUID.randomUUID())
                .build();

        client.updateBeer(beer.getId(), beer);
    }

    @Test
    void testDeleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }

    //Customer

    @Test
    void testGetCustomerById() {
        CustomerDto beer = client.getCustomerById(UUID.randomUUID());
        assertThat(beer.getId()).isNotNull();
    }

    @Test
    void testSaveNewCustomer() {
        CustomerDto beer = CustomerDto.builder().name("alper")
                .build();
        URI uri = client.saveNewCustomer(beer);
        assertNotNull(uri);

        log.info("URI:" + uri.toString());
    }

    void testUpdateCustomer() {
        CustomerDto customer = CustomerDto.builder()
                .name("myBeer")
                .id(UUID.randomUUID())
                .build();

        client.updateCustomer(customer.getId(), customer);
    }

    @Test
    void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }

}