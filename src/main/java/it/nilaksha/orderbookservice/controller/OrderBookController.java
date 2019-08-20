package it.nilaksha.orderbookservice.controller;

import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.service.OrderBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class OrderBookController {

    private final OrderBookService orderBookService;

    @PostMapping
    public OrderBook open(@RequestBody OrderBook orderBook){
        return orderBookService.open(orderBook);
    }

}
