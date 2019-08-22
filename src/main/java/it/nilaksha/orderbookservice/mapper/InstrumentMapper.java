package it.nilaksha.orderbookservice.mapper;

import it.nilaksha.orderbookservice.model.Instrument;
import it.nilaksha.orderbookservice.model.InstrumentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstrumentMapper {

    Instrument entityToDto(InstrumentEntity instrumentEntity);

    InstrumentEntity dtoToEntity(Instrument instrument);

}
