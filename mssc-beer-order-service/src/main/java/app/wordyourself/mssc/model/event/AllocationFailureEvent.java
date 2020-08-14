package app.wordyourself.mssc.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * alper - 14/08/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocationFailureEvent implements Serializable {

    static final long serialVersionUID = 6099605649859274635L;

    private UUID orderId;
}