package app.wordyourself.mssc.common.event;

import lombok.NoArgsConstructor;

/**
 * alper - 10/08/2020
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    static final long serialVersionUID = 5958604664703711326L;


    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
