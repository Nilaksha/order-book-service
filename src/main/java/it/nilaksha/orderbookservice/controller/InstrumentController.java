package it.nilaksha.orderbookservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.nilaksha.orderbookservice.model.Instrument;
import it.nilaksha.orderbookservice.model.Order;
import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Api(tags = "Instrument API", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class InstrumentController {

    private final InstrumentService instrumentService;

    @PostMapping(
            value = "/instruments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Adds an Instrument", response = OrderBook.class)
    public Instrument addInstrument(@RequestBody Instrument instrument) {
        return instrumentService.addInstrument(instrument);
    }

}
