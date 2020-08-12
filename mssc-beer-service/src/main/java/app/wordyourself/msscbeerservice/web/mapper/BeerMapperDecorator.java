package app.wordyourself.msscbeerservice.web.mapper;


import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.service.BeerInventoryService;
import app.wordyourself.mssc.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * alper - 07/08/2020
 */
public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto mapEntityToDto(Beer beer) {
        BeerDto dto = mapper.mapEntityToDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public BeerDto matEntityToDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.mapEntityToDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer mapDtoToEntity(BeerDto beerDto) {
        return mapper.mapDtoToEntity(beerDto);
    }
}