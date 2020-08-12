package app.wordyourself.mssc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * alper - 12/08/2020
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateBeerOrderRequest implements Serializable  {

    static final long serialVersionUID = -3730398481647976239L;

    private UUID id;
    private BeerOrderDto order;
}
