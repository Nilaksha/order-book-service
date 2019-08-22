package it.nilaksha.orderbookservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "order_book")
public class OrderBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private InstrumentEntity instrumentEntity;

    @JoinTable(name = "ob_order", joinColumns = @JoinColumn(name = "order_book_id"))
    @ElementCollection(fetch = FetchType.LAZY)
    private Collection<OrderEntity> orders;

    @JoinTable(name = "ob_execution", joinColumns = @JoinColumn(name = "order_book_id"))
    @ElementCollection(fetch = FetchType.LAZY)
    private Collection<ExecutionEntity> executions;

    @Enumerated(value = EnumType.STRING)
    private OrderBookStatus orderBookStatus;

}