package app.wordyourself.mssc.common.event;

import app.wordyourself.mssc.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * alper - 10/08/2020
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    static final long serialVersionUID = -2165972456580412333L;

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
