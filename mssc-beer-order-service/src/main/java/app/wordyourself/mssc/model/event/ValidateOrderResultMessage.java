package app.wordyourself.mssc.model.event;

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
public class ValidateOrderResultMessage implements Serializable {

    static final long serialVersionUID = -7447044949161998761L;

    UUID orderId;
    Boolean isValid;
}
