package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.mapper.ExecutionMapper;
import it.nilaksha.orderbookservice.mapper.OrderBookMapper;
import it.nilaksha.orderbookservice.mapper.OrderMapper;
import it.nilaksha.orderbookservice.model.*;
import it.nilaksha.orderbookservice.repository.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderBookServiceImpl implements OrderBookService {

    private final OrderBookRepository orderBookRepository;

    private final OrderBookMapper orderBookMapper;

    private final OrderMapper orderMapper;

    private final ExecutionMapper executionMapper;

    @Override
    public OrderBook open(OrderBook orderBook) {
        OrderBookEntity orderBookEntity = orderBookRepository.save(orderBookMapper.dtoToEntity(orderBook));
        return orderBookMapper.entityToDto(orderBookEntity);
    }

    @Override
    public OrderBook close(long orderBookId) {
        OrderBookEntity orderBookEntity;
        Optional<OrderBookEntity> orderBookEntityOptional = orderBookRepository.findById(orderBookId);
        if (orderBookEntityOptional.isPresent()) {
            orderBookEntity = orderBookEntityOptional.get();
            orderBookEntity.setOrderBookStatus(OrderBookStatus.CLOSE);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Book not found");
        }
        OrderBookEntity closedOrderBookEntity = orderBookRepository.save(orderBookEntity);
        return orderBookMapper.entityToDto(closedOrderBookEntity);
    }

    @Override
    public OrderBook addOrder(long orderBookId, Order order) {
        OrderBookEntity orderBookEntity;
        OrderBookEntity savedOrderBookEntity;
        Optional<OrderBookEntity> orderBookEntityOptional = orderBookRepository.findById(orderBookId);
        if (orderBookEntityOptional.isPresent()) {
            orderBookEntity = orderBookEntityOptional.get();
            if (OrderBookStatus.OPEN == orderBookEntity.getOrderBookStatus()) {
                if (OrderType.MARKET_ORDER == order.getOrderType()) {
                    order.setPrice(null);
                } else if (order.getPrice() == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price can't be null when Order Type is Limit Order");
                }
                OrderEntity orderEntity = orderMapper.dtoToEntity(order);
                orderEntity.setId(orderBookEntity.getOrders() == null ? 1 : orderBookEntity.getOrders().size() + 1);
                orderBookEntity.getOrders().add(orderEntity);
                savedOrderBookEntity = orderBookRepository.save(orderBookEntity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Order Book is already Closed");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Book not found");
        }
        return orderBookMapper.entityToDto(savedOrderBookEntity);
    }

    @Override
    public OrderBook addExecution(long orderBookId, Execution execution) {

        OrderBookEntity orderBookEntity;
        OrderBookEntity savedOrderBookEntity;
        Optional<OrderBookEntity> orderBookEntityOptional = orderBookRepository.findById(orderBookId);
        if (orderBookEntityOptional.isPresent()) {
            orderBookEntity = orderBookEntityOptional.get();
            if (OrderBookStatus.CLOSE == orderBookEntity.getOrderBookStatus()) {
                ExecutionEntity executionEntity = executionMapper.dtoToEntity(execution);
                executionEntity.setId(orderBookEntity.getExecutions() == null ? 1 : orderBookEntity.getExecutions().size() + 1);
                orderBookEntity.getExecutions().add(executionEntity);
                savedOrderBookEntity = orderBookRepository.save(orderBookEntity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Order Book is still Open");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Book not found");
        }
        return orderBookMapper.entityToDto(savedOrderBookEntity);
    }

}
