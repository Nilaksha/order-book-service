package it.nilaksha.orderbookservice.repository;

import it.nilaksha.orderbookservice.model.OrderBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookRepository extends JpaRepository<OrderBookEntity, Long> {

}
