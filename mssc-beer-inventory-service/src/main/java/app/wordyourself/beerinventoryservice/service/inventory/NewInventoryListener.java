package app.wordyourself.beerinventoryservice.service.inventory;

import app.wordyourself.beerinventoryservice.config.JmsConfig;
import app.wordyourself.beerinventoryservice.domain.BeerInventory;
import app.wordyourself.beerinventoryservice.repositories.BeerInventoryRepository;
import app.wordyourself.mssc.model.BeerDto;
import app.wordyourself.mssc.common.event.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * alper - 11/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {

    private final BeerInventoryRepository repository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {

        log.debug("New Inventory" + event.toString());
        BeerDto beer = event.getBeerDto();

        BeerInventory inventory = BeerInventory.builder()
                .beerId(beer.getId())
                .upc(beer.getUpc())
                .quantityOnHand(beer.getQuantityOnHand())
                .build();

        repository.save(inventory);


    }
}
