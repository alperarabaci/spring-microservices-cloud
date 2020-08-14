package app.wordyourself.beerorderservice.services;


import app.wordyourself.mssc.model.CustomerPagedList;
import org.springframework.data.domain.Pageable;

/**
 * alper - 14/08/2020
 */
public interface CustomerService {

    CustomerPagedList listCustomers(Pageable pageable);

}
