package app.wordyourself.trainingjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * alper - 10/08/2020
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelloWorldMessge implements Serializable {

    static final long serialVersionUID = 4635545795971883592L;

    private UUID id;
    private String message;

}
