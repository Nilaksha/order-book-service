package it.nilaksha.orderbookservice.service;

import it.nilaksha.orderbookservice.mapper.ExecutionMapper;
import it.nilaksha.orderbookservice.mapper.OrderBookMapper;
import it.nilaksha.orderbookservice.mapper.OrderMapper;
import it.nilaksha.orderbookservice.model.*;
import it.nilaksha.orderbookservice.repository.OrderBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderBookServiceImplTest {

    @Mock
    private OrderBookRepository orderBookRepository;

    @Mock
    private OrderBookMapper orderBookMapper;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private ExecutionMapper executionMapper;

    private OrderBookService orderBookService;

    @BeforeEach
    void init() {
        orderBookService = new OrderBookServiceImpl(orderBookRepository, orderBookMapper, orderMapper, executionMapper);
    }

    @Test
    void testOpen() {

        OrderBook orderBook = new OrderBook();
        orderBook.setInstrumentId(1L);
        orderBook.setOrderBookStatus(OrderBookStatus.OPEN);

        InstrumentEntity instrumentEntity = new InstrumentEntity();
        instrumentEntity.setId(1L);
        instrumentEntity.setName("Cash Deposit");

        OrderBookEntity orderBookEntity = new OrderBookEntity();
        orderBookEntity.setInstrumentEntity(instrumentEntity);
        orderBookEntity.setOrderBookStatus(OrderBookStatus.OPEN);

        when(orderBookMapper.dtoToEntity(orderBook)).thenReturn(orderBookEntity);
        when(orderBookRepository.save(any(OrderBookEntity.class))).then(returnsFirstArg());
        when(orderBookMapper.entityToDto(orderBookEntity)).thenReturn(orderBook);

        OrderBook openedOrderBook = orderBookService.open(orderBook);

        assertNotNull(openedOrderBook);
        assertEquals(OrderBookStatus.OPEN, openedOrderBook.getOrderBookStatus());
        assertEquals(1L, openedOrderBook.getInstrumentId().longValue());

    }

    @Test
    void testClose() {

        OrderBook orderBook = new OrderBook();
        orderBook.setInstrumentId(1L);
        orderBook.setOrderBookStatus(OrderBookStatus.OPEN);

        InstrumentEntity instrumentEntity = new InstrumentEntity();
        instrumentEntity.setId(1L);
        instrumentEntity.setName("Cash Deposit");

        OrderBookEntity orderBookEntity = new OrderBookEntity();
        orderBookEntity.setInstrumentEntity(instrumentEntity);
        orderBookEntity.setOrderBookStatus(OrderBookStatus.OPEN);

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.of(orderBookEntity));
        when(orderBookRepository.save(any(OrderBookEntity.class))).then(returnsFirstArg());
        when(orderBookMapper.entityToDto(orderBookEntity)).thenReturn(orderBook);

        OrderBook closedOrderBook = orderBookService.close(1L);

        assertNotNull(closedOrderBook);
        assertEquals(OrderBookStatus.OPEN, closedOrderBook.getOrderBookStatus());
        assertEquals(1L, closedOrderBook.getInstrumentId().longValue());

    }

    @Test
    void testCloseWhenOrderBookNotFound() {

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> orderBookService.close(1L));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());

    }

    @Test
    void testAddOrder() {

        Date date = new Date();

        Order order = new Order();
        order.setOrderType(OrderType.LIMIT_ORDER);
        order.setPrice(10.50);
        order.setQuantity(10);
        order.setEntryDate(date);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderType(OrderType.LIMIT_ORDER);
        orderEntity.setPrice(10.50);
        orderEntity.setQuantity(10);
        orderEntity.setEntryDate(date);

        OrderBook orderBook = new OrderBook();
        orderBook.setInstrumentId(1L);
        orderBook.setOrderBookStatus(OrderBookStatus.OPEN);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orderBook.setOrders(orders);

        InstrumentEntity instrumentEntity = new InstrumentEntity();
        instrumentEntity.setId(1L);
        instrumentEntity.setName("Cash Deposit");

        OrderBookEntity orderBookEntity = new OrderBookEntity();
        orderBookEntity.setInstrumentEntity(instrumentEntity);
        orderBookEntity.setOrderBookStatus(OrderBookStatus.OPEN);
        orderBookEntity.setOrders(new ArrayList<>());

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.of(orderBookEntity));
        when(orderMapper.dtoToEntity(order)).thenReturn(orderEntity);
        when(orderBookRepository.save(any(OrderBookEntity.class))).then(returnsFirstArg());
        when(orderBookMapper.entityToDto(orderBookEntity)).thenReturn(orderBook);

        OrderBook addedOrderBook = orderBookService.addOrder(1L, order);

        assertNotNull(addedOrderBook);
        assertEquals(OrderBookStatus.OPEN, addedOrderBook.getOrderBookStatus());
        assertEquals(1L, addedOrderBook.getInstrumentId().longValue());
        assertEquals(OrderType.LIMIT_ORDER, new ArrayList<>(addedOrderBook.getOrders()).get(0).getOrderType());
        assertEquals(10.50, new ArrayList<>(addedOrderBook.getOrders()).get(0).getPrice().doubleValue());
        assertEquals(10, new ArrayList<>(addedOrderBook.getOrders()).get(0).getQuantity().intValue());
        assertEquals(date, new ArrayList<>(addedOrderBook.getOrders()).get(0).getEntryDate());

    }

    @Test
    void testAddOrderWhenOrderBookNotFound() {

        Date date = new Date();

        Order order = new Order();
        order.setOrderType(OrderType.LIMIT_ORDER);
        order.setPrice(10.50);
        order.setQuantity(10);
        order.setEntryDate(date);

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> orderBookService.addOrder(1L, order));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());

    }

    @Test
    void testAddOrderWhenOrderBookIsClosed() {

        Date date = new Date();

        Order order = new Order();
        order.setOrderType(OrderType.LIMIT_ORDER);
        order.setPrice(10.50);
        order.setQuantity(10);
        order.setEntryDate(date);

        InstrumentEntity instrumentEntity = new InstrumentEntity();
        instrumentEntity.setId(1L);
        instrumentEntity.setName("Cash Deposit");

        OrderBookEntity orderBookEntity = new OrderBookEntity();
        orderBookEntity.setInstrumentEntity(instrumentEntity);
        orderBookEntity.setOrderBookStatus(OrderBookStatus.CLOSE);
        orderBookEntity.setOrders(new ArrayList<>());

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.of(orderBookEntity));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> orderBookService.addOrder(1L, order));

        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());

    }

    @Test
    void testAddExecution() {

        Execution execution = new Execution();
        execution.setPrice(20.50);
        execution.setQuantity(20);

        ExecutionEntity executionEntity = new ExecutionEntity();
        executionEntity.setPrice(20.50);
        executionEntity.setQuantity(20);

        OrderBook orderBook = new OrderBook();
        orderBook.setInstrumentId(1L);
        orderBook.setOrderBookStatus(OrderBookStatus.OPEN);
        List<Execution> executions = new ArrayList<>();
        executions.add(execution);
        orderBook.setExecutions(executions);

        InstrumentEntity instrumentEntity = new InstrumentEntity();
        instrumentEntity.setId(1L);
        instrumentEntity.setName("Cash Deposit");

        OrderBookEntity orderBookEntity = new OrderBookEntity();
        orderBookEntity.setInstrumentEntity(instrumentEntity);
        orderBookEntity.setOrderBookStatus(OrderBookStatus.CLOSE);
        orderBookEntity.setExecutions(new ArrayList<>());

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.of(orderBookEntity));
        when(executionMapper.dtoToEntity(execution)).thenReturn(executionEntity);
        when(orderBookRepository.save(any(OrderBookEntity.class))).then(returnsFirstArg());
        when(orderBookMapper.entityToDto(orderBookEntity)).thenReturn(orderBook);

        OrderBook addedOrderBook = orderBookService.addExecution(1L, execution);

        assertNotNull(addedOrderBook);
        assertEquals(OrderBookStatus.OPEN, addedOrderBook.getOrderBookStatus());
        assertEquals(1L, addedOrderBook.getInstrumentId().longValue());
        assertEquals(20, new ArrayList<>(addedOrderBook.getExecutions()).get(0).getQuantity().intValue());
        assertEquals(20.50, new ArrayList<>(addedOrderBook.getExecutions()).get(0).getPrice().doubleValue());

    }

    @Test
    void testAddExecutionWhenOrderBookNotFound() {

        Execution execution = new Execution();
        execution.setPrice(20.50);
        execution.setQuantity(20);

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> orderBookService.addExecution(1L, execution));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());

    }

    @Test
    void testAddExecutionWhenOrderBookIsOpen() {

        Execution execution = new Execution();
        execution.setPrice(20.50);
        execution.setQuantity(20);

        InstrumentEntity instrumentEntity = new InstrumentEntity();
        instrumentEntity.setId(1L);
        instrumentEntity.setName("Cash Deposit");

        OrderBookEntity orderBookEntity = new OrderBookEntity();
        orderBookEntity.setInstrumentEntity(instrumentEntity);
        orderBookEntity.setOrderBookStatus(OrderBookStatus.OPEN);
        orderBookEntity.setOrders(new ArrayList<>());

        when(orderBookRepository.findById(anyLong())).thenReturn(Optional.of(orderBookEntity));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> orderBookService.addExecution(1L, execution));

        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());

    }

}