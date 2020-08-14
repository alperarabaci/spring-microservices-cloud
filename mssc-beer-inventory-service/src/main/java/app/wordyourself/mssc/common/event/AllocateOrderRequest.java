package app.wordyourself.mssc.common.event;

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
public class AllocateOrderRequest implements Serializable {

    static final long serialVersionUID = 6105703272245231228L;

    private BeerOrderDto beerOrderDto;
}