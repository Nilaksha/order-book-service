package it.nilaksha.orderbookservice.mapper;

import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.model.OrderBookEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderBookMapper {

    OrderBook entityToDto(OrderBookEntity orderBookEntity);

    @Mapping(target = "id", ignore = true)
    OrderBookEntity dtoToEntity(OrderBook orderBook);

}
