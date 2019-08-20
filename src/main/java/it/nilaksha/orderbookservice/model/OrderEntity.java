package it.nilaksha.orderbookservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @ManyToOne
    private OrderBookEntity orderBookEntity;

    private Integer quantity;

    private Double price;

    private Date entryDate;

}
