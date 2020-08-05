package app.wordyourself.msscbeerservice.repository;

import app.wordyourself.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * alper - 05/08/2020
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {


}
