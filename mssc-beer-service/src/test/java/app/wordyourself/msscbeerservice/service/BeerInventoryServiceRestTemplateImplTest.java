package app.wordyourself.msscbeerservice.service;

import app.wordyourself.msscbeerservice.service.inventory.BeerInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {
        //Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);

        //System.out.println(qoh);

    }
}