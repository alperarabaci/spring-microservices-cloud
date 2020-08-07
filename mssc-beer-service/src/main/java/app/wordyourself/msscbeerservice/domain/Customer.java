package app.wordyourself.msscbeerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * alper - 06/08/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    UUID id;
    String name;

}