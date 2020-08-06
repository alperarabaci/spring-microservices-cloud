package app.wordyourself.msscbrewery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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