package app.wordyourself.msscbeerservice.service.inventory;

import app.wordyourself.msscbeerservice.service.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * alper - 15/08/2020
 */
@FeignClient
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value= BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(UUID beerId);

}
