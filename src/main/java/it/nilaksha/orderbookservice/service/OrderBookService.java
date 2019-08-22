package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.model.Execution;
import it.nilaksha.orderbookservice.model.Order;
import it.nilaksha.orderbookservice.model.OrderBook;

public interface OrderBookService {

    OrderBook open(OrderBook orderBook);

    OrderBook close(long orderBookId);

    OrderBook addOrder(long orderBookId, Order order);

    OrderBook addExecution(long orderBookId, Execution execution);

}
