package app.wordyourself.mssc.common.event;

import app.wordyourself.mssc.model.BeerDto;
import lombok.*;

import java.io.Serializable;

/**
 * alper - 10/08/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -2043749079573975403L;

    private BeerDto beerDto;


}
