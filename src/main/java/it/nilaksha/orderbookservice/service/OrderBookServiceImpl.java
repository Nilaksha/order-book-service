package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.mapper.OrderBookMapper;
import it.nilaksha.orderbookservice.model.Order;
import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.model.OrderBookEntity;
import it.nilaksha.orderbookservice.model.OrderBookStatus;
import it.nilaksha.orderbookservice.repository.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderBookServiceImpl implements OrderBookService {

    private  final OrderBookMapper orderBookMapper;

    private final OrderBookRepository orderBookRepository;

    @Override
    public OrderBook open(OrderBook orderBook) {
        OrderBookEntity orderBookEntity = orderBookRepository.save(orderBookMapper.dtoToEntity(orderBook));
        return orderBookMapper.entityToDto(orderBookEntity);
    }

    @Override
    public OrderBook close(long orderBookId) {
        OrderBookEntity orderBookEntity = null;
        Optional<OrderBookEntity> orderBookEntityOptional = orderBookRepository.findById(orderBookId);
        if (orderBookEntityOptional.isPresent()){
            orderBookEntity = orderBookEntityOptional.get();
        }
        OrderBookEntity closedOrderBookEntity = orderBookRepository.save(orderBookEntity);
        return orderBookMapper.entityToDto(closedOrderBookEntity);
    }

    @Override
    public void addOrder(long orderBookId, Order order) {

    }

}
