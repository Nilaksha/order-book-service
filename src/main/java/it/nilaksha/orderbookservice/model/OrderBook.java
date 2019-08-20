package it.nilaksha.orderbookservice.model;

import lombok.Data;

import java.util.Collection;

@Data
public class OrderBook {

    private Long id;

    private Collection<Order> orders;

}
