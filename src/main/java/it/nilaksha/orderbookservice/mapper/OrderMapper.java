package it.nilaksha.orderbookservice.mapper;

import it.nilaksha.orderbookservice.model.Order;
import it.nilaksha.orderbookservice.model.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order entityToDto(OrderEntity orderEntity);

    @Mapping(target = "id", ignore = true)
    OrderEntity dtoToEntity(Order order);

}
