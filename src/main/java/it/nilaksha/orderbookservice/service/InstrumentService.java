package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.model.Instrument;

import java.util.List;

public interface InstrumentService {

    Instrument addInstrument(Instrument instrument);

    Instrument getInstrument(long instrumentId);

    List<Instrument> getInstruments();

    Instrument updateInstrument(long instrumentId, Instrument instrument);

    void deleteInstrument(long instrumentId);

}
