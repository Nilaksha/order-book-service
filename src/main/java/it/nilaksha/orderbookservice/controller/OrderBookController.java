package it.nilaksha.orderbookservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.nilaksha.orderbookservice.model.Execution;
import it.nilaksha.orderbookservice.model.Order;
import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.service.OrderBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orderBooks")
@RequiredArgsConstructor
@Api(tags = "Order Book API", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderBookController {

    private final OrderBookService orderBookService;

    @PostMapping(
            value = "/open",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Opens an Order Book", response = OrderBook.class )
    public OrderBook open(@RequestBody OrderBook orderBook) {
        return orderBookService.open(orderBook);
    }

    @PostMapping(
            value = "/close/{orderBookId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Closes an Order Book", response = OrderBook.class )
    public OrderBook close(@PathVariable("orderBookId") long orderBookId) {
        return orderBookService.close(orderBookId);
    }

    @PostMapping(
            value = "/addOrder/{orderBookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Adds Order to an Order Book", response = HttpStatus.class )
    public ResponseEntity<?> addOrder(@PathVariable("orderBookId") long orderBookId, @RequestBody Order order) {
        orderBookService.addOrder(orderBookId, order);
        return ResponseEntity.ok().build();
    }

    @PostMapping(
            value = "/addExecution/{orderBookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Adds Order to an Order Book", response = HttpStatus.class )
    public ResponseEntity<?> addExecution(@PathVariable("orderBookId") long orderBookId, @RequestBody Execution execution) {
        orderBookService.addExecution(orderBookId, execution);
        return ResponseEntity.ok().build();
    }

}
