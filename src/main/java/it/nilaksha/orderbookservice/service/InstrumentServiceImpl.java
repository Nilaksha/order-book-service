package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.mapper.InstrumentMapper;
import it.nilaksha.orderbookservice.model.Instrument;
import it.nilaksha.orderbookservice.model.InstrumentEntity;
import it.nilaksha.orderbookservice.repository.InstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;

    private final InstrumentMapper instrumentMapper;

    @Override
    public Instrument addInstrument(Instrument instrument) {
        InstrumentEntity instrumentEntity = instrumentMapper.dtoToEntity(instrument);
        return instrumentMapper.entityToDto(instrumentRepository.save(instrumentEntity));
    }

    @Override
    public Instrument getInstrument(long instrumentId) {
        return null;
    }

    @Override
    public List<Instrument> getInstruments() {
        return null;
    }

    @Override
    public Instrument updateInstrument(long instrumentId, Instrument instrument) {
        return null;
    }

    @Override
    public void deleteInstrument(long instrumentId) {

    }
}
