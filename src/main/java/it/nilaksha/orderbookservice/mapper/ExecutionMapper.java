package it.nilaksha.orderbookservice.mapper;

import it.nilaksha.orderbookservice.model.Execution;
import it.nilaksha.orderbookservice.model.ExecutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExecutionMapper {

    Execution entityToDto(ExecutionEntity executionEntity);

    @Mapping(target = "id", ignore = true)
    ExecutionEntity dtoToEntity(Execution execution);

}
