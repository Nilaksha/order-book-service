package it.nilaksha.orderbookservice.repository;

import it.nilaksha.orderbookservice.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}