package app.wordyourself.msscbeerservice.bootsrap;

import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import app.wordyourself.msscbeerservice.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * alper - 05/08/2020
 */
@Component
@Slf4j
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        Beer beer = Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.LAGER.toString())
                .quantityToBrew(200)
                .minOnHand(12)
                .price(new BigDecimal("12.95"))
                .upc(1238218328000L)
                .build();
        beerRepository.save(beer);

        Beer beer2 = Beer.builder()
                .beerName("Galaxy Dog")
                .beerStyle(BeerStyleEnum.IPA.toString())
                .quantityToBrew(200)
                .minOnHand(12)
                .price(new BigDecimal("5.95"))
                .upc(12382183289999L)
                .build();
        beerRepository.save(beer2);

        log.info("Beers loaded: " + beerRepository.count());
    }
}
