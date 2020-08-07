package app.wordyourself.msscbeerservice.bootsrap;

import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import app.wordyourself.msscbeerservice.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@Slf4j
public class BeerLoader implements CommandLineRunner {

    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //using data.sql file now
    }

    /*
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
    */
}
