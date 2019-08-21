package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.model.Order;
import it.nilaksha.orderbookservice.model.OrderBook;
import org.springframework.http.ResponseEntity;

public interface OrderBookService {

    OrderBook open(OrderBook orderBook);

    OrderBook close(long orderBookId);

    void addOrder(long orderBookId, Order order);

}
