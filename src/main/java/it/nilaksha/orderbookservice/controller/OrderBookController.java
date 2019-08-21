package it.nilaksha.orderbookservice.controller;

import io.swagger.annotations.Api;
import it.nilaksha.orderbookservice.model.Order;
import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.service.OrderBookService;
import lombok.RequiredArgsConstructor;
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
    public OrderBook open(@RequestBody OrderBook orderBook) {
        return orderBookService.open(orderBook);
    }

    @PostMapping(
            value = "/close",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OrderBook close(@PathVariable("orderBookId") long orderBookId) {
        return orderBookService.close(orderBookId);
    }

    @PostMapping(
            value = "/addOrder",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addOrder(@PathVariable("orderBookId") long orderBookId, @RequestBody Order order) {
        orderBookService.addOrder(orderBookId, order);
        return ResponseEntity.ok().build();
    }

}
