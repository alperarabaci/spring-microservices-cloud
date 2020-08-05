package app.wordyourself.msscbreweryclient.web.client;

import app.wordyourself.msscbreweryclient.web.model.BeerDto;
import app.wordyourself.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@Component
@ConfigurationProperties(value = "app.wordyourself", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beer) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beer);
    }

    public void updateBeer(UUID id, BeerDto beer){
        restTemplate.put(apihost + BEER_PATH_V1 + id.toString(), beer);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(apihost + BEER_PATH_V1 + id.toString());
    }

    //Customer
    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customer) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customer);
    }

    public void updateCustomer(UUID id, CustomerDto customer){
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + id.toString(), customer);
    }

    public void deleteCustomer(UUID id) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + id.toString());
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }


}
