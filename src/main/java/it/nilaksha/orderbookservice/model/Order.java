package it.nilaksha.orderbookservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private Long id;

    private Integer quantity;

    private Double price;

    private Date entryDate;

}
