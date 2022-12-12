package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.BookOrder;
import com.example.demo.entity.BookStock;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.exception.CustomValidationException;
import com.example.demo.mapper.BookOrderMapper;
import com.example.demo.mapper.CustomerOrderMapper;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.demo.service.BookServiceTest.getSingletonPageOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private CustomerOrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CustomerOrderMapper customerOrderMapper;
    @Mock
    private BookOrderMapper bookOrderMapper;

    @Test
     void test_add_then_book_order_exception() {
        final CustomValidationException ex = assertThrows(CustomValidationException.class,
                () -> orderService.add(new CustomerOrderDto()));
        assertEquals("valid.book.orders.empty", ex.getMessage());
    }

    @Test
     void test_add_then_customer_exception() {
        final CustomerOrderDto orderDto = new CustomerOrderDto();
        orderDto.setBookOrders(Collections.singletonList(new BookOrderDto()));
        final CustomValidationException ex = assertThrows(CustomValidationException.class,
                () -> orderService.add(orderDto));
        assertEquals("valid.customer.not.exist", ex.getMessage());
    }

    @Test
     void test_add_then_stock_exception() {
        final BookStock book = new BookStock();
        book.setQuantity(0L);
        book.setId(1L);
        final CustomerOrderDto orderDto = getCustomerOrderDto(book);
        final CustomValidationException ex = assertThrows(CustomValidationException.class,
                () -> orderService.add(orderDto));
        assertEquals("valid.book.stock.not.exist", ex.getMessage());
    }

    @Test
     void test_add_then_stock_ok() {
        final BookStock book = new BookStock();
        book.setQuantity(5L);
        book.setPrice(10D);
        book.setId(1L);
        final CustomerOrderDto orderDto = getCustomerOrderDto(book);
        when(customerOrderMapper.toDTO(any()))
                .thenReturn(orderDto);

        assertNotNull(orderService.add(orderDto));
    }

    @Test
     void test_get_then_ok() {
        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(new CustomerOrder()));
        when(customerOrderMapper.toDTO(any()))
                .thenReturn(new CustomerOrderDto());
        assertNotNull(orderService.getOrderById(1L));
    }

    @Test
     void test_search_then_exception() {
        final OrderSearchRequest searchRequest = new OrderSearchRequest();
        searchRequest.setStartDate(LocalDateTime.now());
        searchRequest.setEndDate(LocalDateTime.now().minus(1, ChronoUnit.DAYS));
        assertThrows(CustomValidationException.class,
                () -> orderService.getOrdersBetweenDates(searchRequest));
    }

    @Test
     void test_search_then_ok() {
        when(customerOrderMapper.toDTO(any()))
                .thenReturn(new CustomerOrderDto());
        when(orderRepository.findCustomerOrderByCreateDateTimeBetween(any(), any(), any()))
                .thenReturn(getSingletonPageOf(CustomerOrder.class));
        final OrderSearchRequest searchRequest = new OrderSearchRequest();
        searchRequest.setPageRequestInfo(PageRequestInfo.of(0, 5));
        searchRequest.setStartDate(LocalDateTime.now());
        searchRequest.setEndDate(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        assertNotNull(orderService.getOrdersBetweenDates(searchRequest));
    }

    @Test
     void test_statistics_then_ok() {
        when(orderRepository.findOrderStatistics())
                .thenReturn(Collections.singletonList(new OrderStatistics()));
        assertFalse(orderService.getStatistics().isEmpty());
    }

    private CustomerOrderDto getCustomerOrderDto(BookStock book) {
        final CustomerOrderDto orderDto = new CustomerOrderDto();
        orderDto.setCustomerId(1L);
        orderDto.setBookOrders(Collections.singletonList(new BookOrderDto()));
        final Customer customer = new Customer();
        final CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        final BookOrder bookOrder = new BookOrder();
        bookOrder.setBook(book);
        bookOrder.setQuantity(1L);
        order.setBookOrders(Collections.singletonList(bookOrder));
        when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(customer));
        when(bookRepository.findById(anyLong()))
                .thenReturn(Optional.of(book));
        when(customerOrderMapper.toEntity(any()))
                .thenReturn(order);
        return orderDto;
    }
}
