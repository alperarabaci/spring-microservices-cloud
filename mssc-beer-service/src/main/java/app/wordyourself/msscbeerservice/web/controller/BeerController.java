package app.wordyourself.msscbeerservice.web.controller;

import app.wordyourself.msscbeerservice.web.model.BeerDto;
import app.wordyourself.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * alper - 05/08/2020
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        BeerDto beer = BeerDto.builder().beerName("MyBeer")
                .id(UUID.randomUUID())
                .beerStyle(BeerStyleEnum.LAGER)
                .build();
        return new ResponseEntity<>(beer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody @Valid BeerDto beerDto) {

        String id = UUID.randomUUID().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:8080/api/v1/beer/" + id);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@PathVariable UUID beerId, @RequestBody @Valid BeerDto beerDto) {

    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable UUID beerId) {

    }
}
