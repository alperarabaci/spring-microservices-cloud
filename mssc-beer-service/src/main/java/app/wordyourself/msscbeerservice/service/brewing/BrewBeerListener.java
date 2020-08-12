package app.wordyourself.msscbeerservice.service.brewing;

import app.wordyourself.msscbeerservice.config.JmsConfig;
import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.mssc.common.event.BrewBeerEvent;
import app.wordyourself.mssc.common.event.NewInventoryEvent;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import app.wordyourself.mssc.model.BeerDto;
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
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer" + beer.getMinOnHand() + ": QQH: " +beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);

    }
}
