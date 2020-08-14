package app.wordyourself.mssc.model.event;

import app.wordyourself.mssc.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * alper - 14/08/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeallocateOrderRequest implements Serializable {

    static final long serialVersionUID = 2330254803990517367L;

    private BeerOrderDto beerOrderDto;
}