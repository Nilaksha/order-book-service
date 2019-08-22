package it.nilaksha.orderbookservice.mapper;

import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.model.OrderBookEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {OrderMapper.class, ExecutionMapper.class, OrderBookMapperResolver.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderBookMapper {

    @Mapping(source = "instrumentEntity.id", target = "instrumentId")
    @Mapping(target = "orders", ignore = true)
    OrderBook entityToDto(OrderBookEntity orderBookEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "instrumentId", target = "instrumentEntity")
    OrderBookEntity dtoToEntity(OrderBook orderBook);

}
