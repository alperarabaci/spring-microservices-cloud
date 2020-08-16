package app.wordyourself.inventoryfailover.web.controller;

import app.wordyourself.mssc.common.event.BeerInventoryDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * alper - 16/08/2020
 */

@RestController
public class InventoryFailoverController {

    @RequestMapping("inventory-failover")
    public BeerInventoryDto get(){
        BeerInventoryDto dto = new BeerInventoryDto();

        UUID zeroUUID = new UUID(0,0);
        BeerInventoryDto inventory = dto.builder()
                .id(UUID.randomUUID())
                .quantityOnHand(999)
                .beerId(zeroUUID)
                .build();

        return inventory;
    }

}
