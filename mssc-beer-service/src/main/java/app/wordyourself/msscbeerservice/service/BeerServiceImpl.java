package app.wordyourself.msscbeerservice.service;

import app.wordyourself.msscbeerservice.domain.Beer;
import app.wordyourself.msscbeerservice.repository.BeerRepository;
import app.wordyourself.msscbeerservice.web.controller.NotFoundException;
import app.wordyourself.msscbeerservice.web.mapper.BeerMapper;
import app.wordyourself.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * alper - 06/08/2020
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repo;
    private final BeerMapper mapper;

    @Override
    public BeerDto getById(UUID beerId) {
        Beer beer = repo.findById(beerId).orElseThrow(NotFoundException::new);
        return mapper.mapEntityToDto(beer);
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
