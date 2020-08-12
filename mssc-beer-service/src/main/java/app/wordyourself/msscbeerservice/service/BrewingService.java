package app.wordyourself.msscbeerservice.service;

import app.wordyourself.msscbeerservice.config.JmsConfig;
import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.mssc.common.event.BrewBeerEvent;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import app.wordyourself.msscbeerservice.web.mapper.BeerMapper;
import app.wordyourself.mssc.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * alper - 11/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {


    private final BeerRepository repository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInverntory() {
        List<Beer> beers = repository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("Min Onhand is:" + beer.getMinOnHand());
            log.debug("Inventory is:" + invQOH);

            if(beer.getMinOnHand() >= invQOH) {
                BeerDto beerDto = beerMapper.mapEntityToDto(beer);
                BrewBeerEvent event = new BrewBeerEvent(beerDto);
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, event);
            }
        });

    }
}
