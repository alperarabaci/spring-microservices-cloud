package app.wordyourself.msscbeerservice.bootsrap;

import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import app.wordyourself.mssc.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * alper - 05/08/2020
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        long count = beerRepository.count();
        if(count==0){
            loadBeerObjects();
        }
    }


    private void loadBeerObjects() {
        Beer beer = Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.LAGER.toString())
                .quantityToBrew(200)
                .minOnHand(12)
                .price(new BigDecimal("12.95"))
                .upc(BEER_1_UPC)
                .build();
        beerRepository.save(beer);

        Beer beer2 = Beer.builder()
                .beerName("Galaxy Dog")
                .beerStyle(BeerStyleEnum.IPA.toString())
                .quantityToBrew(200)
                .minOnHand(12)
                .price(new BigDecimal("5.95"))
                .upc(BEER_2_UPC)
                .build();
        beerRepository.save(beer2);

        Beer beer3 = Beer.builder()
                .beerName("No Hammers On The Bar")
                .beerStyle(BeerStyleEnum.IPA.toString())
                .quantityToBrew(200)
                .minOnHand(12)
                .price(new BigDecimal("5.95"))
                .upc(BEER_3_UPC)
                .build();
        beerRepository.save(beer3);

        log.info("Beers loaded: " + beerRepository.count());
    }

}
