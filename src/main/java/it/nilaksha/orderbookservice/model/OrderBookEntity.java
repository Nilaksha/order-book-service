package it.nilaksha.orderbookservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "order_book")
public class OrderBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @OneToOne
    private InstrumentEntity instrumentEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_book_id")
    private Collection<OrderEntity> orderEntities;

    private OrderBookStatus orderBookStatus;

}