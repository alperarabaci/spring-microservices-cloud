package app.wordyourself.mssc.model.event;

import app.wordyourself.mssc.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * alper - 12/08/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderRequest implements Serializable {

    static final long serialVersionUID = -2715980318882476003L;

    private BeerOrderDto beerOrderDto;
}