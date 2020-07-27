package app.wordyourself.msscbrewery.service;

import app.wordyourself.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId);

}
