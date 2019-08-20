package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.mapper.OrderBookMapper;
import it.nilaksha.orderbookservice.model.OrderBook;
import it.nilaksha.orderbookservice.model.OrderBookEntity;
import it.nilaksha.orderbookservice.repository.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
