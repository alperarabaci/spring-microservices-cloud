package app.wordyourself.msscbeerservice.service;

import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import app.wordyourself.msscbeerservice.web.controller.NotFoundException;
import app.wordyourself.msscbeerservice.web.mapper.BeerMapper;
import app.wordyourself.msscbeerservice.web.model.BeerDto;
import app.wordyourself.msscbeerservice.web.model.BeerPagedList;
import app.wordyourself.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * alper - 06/08/2020
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repo;
    private final BeerMapper mapper;

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = repo.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = repo.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = repo.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = repo.findAll(pageRequest);
        }

        if (showInventoryOnHand){
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(mapper::matEntityToDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        } else {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(mapper::mapEntityToDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }

        return beerPagedList;
    }

    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return mapper.matEntityToDtoWithInventory(
                    repo.findById(beerId).orElseThrow(NotFoundException::new)
            );
        } else {
            return mapper.mapEntityToDto(
                    repo.findById(beerId).orElseThrow(NotFoundException::new)
            );
        }
    }

    @Override
    public BeerDto save(BeerDto beerDto) {
        Beer beer = mapper.mapDtoToEntity(beerDto);
        repo.save(beer);
        return mapper.mapEntityToDto(beer);
    }

    @Override
    public BeerDto update(UUID beerId, BeerDto beerDto) {
        Beer beer = repo.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return mapper.mapEntityToDto(repo.save(beer));
    }
}
