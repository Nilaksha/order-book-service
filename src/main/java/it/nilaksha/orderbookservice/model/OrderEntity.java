package it.nilaksha.orderbookservice.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@Embeddable
public class OrderEntity {

    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;

    private Integer quantity;

    private Double price;

    private Date entryDate;

}
