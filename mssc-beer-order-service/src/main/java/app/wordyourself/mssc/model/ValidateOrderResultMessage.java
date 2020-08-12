package app.wordyourself.mssc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * alper - 12/08/2020
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOrderResultMessage {
    UUID orderId;
    Boolean isValid;
}
