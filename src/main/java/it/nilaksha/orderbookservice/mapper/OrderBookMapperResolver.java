package it.nilaksha.orderbookservice.mapper;

import it.nilaksha.orderbookservice.model.InstrumentEntity;
import it.nilaksha.orderbookservice.repository.InstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class OrderBookMapperResolver {

    private final InstrumentRepository instrumentRepository;

    public InstrumentEntity findByInstrumentId(long instrumentId){
        return instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrument not found"));
    }

}
