package it.nilaksha.orderbookservice.repository;

import it.nilaksha.orderbookservice.model.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<InstrumentEntity, Long> {

}
