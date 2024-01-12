package com.example.demo.service.impl;

import com.example.demo.aspect.annotation.Logging;
import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.OrderSearchRequest;
import com.example.demo.dto.OrderStatistics;
import com.example.demo.entity.BookOrder;
import com.example.demo.entity.BookStock;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.enumeration.ORDER_STATUS;
import com.example.demo.exception.CustomValidationException;
import com.example.demo.mapper.CustomerOrderMapper;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * customer order service implementation
 */
@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final CustomerOrderMapper customerOrderMapper;

    @Override
    @Logging
    public CustomerOrderDto add(CustomerOrderDto customerDto) {
        if (CollectionUtils.isEmpty(customerDto.getBookOrders())) {
           throw new CustomValidationException("valid.book.orders.empty");
        }
        final Customer customer = Optional.ofNullable(customerDto)
                .map(CustomerOrderDto::getCustomerId)
                .flatMap(customerRepository::findById)
                .filter(Objects::nonNull)
                .orElseThrow(() ->
                        new CustomValidationException("valid.customer.not.exist", customerDto,
                                CustomerOrderDto::getCustomerId));
        final CustomerOrder customerOrder = customerOrderMapper.toEntity(customerDto);
        customerOrder.setCustomer(customer);
        final double orderPrice = customerOrder.getBookOrders().stream()
                .filter(Objects::nonNull)
                .peek(this::getAndUpdateBook)
                .mapToDouble(bookOrder -> bookOrder.getQuantity() * bookOrder.getPrice())
                .sum();
        customerOrder.setPrice(orderPrice);

        customerOrder.setStatus(ORDER_STATUS.ACTIVE);
        try {
            return customerOrderMapper.toDTO(orderRepository.save(customerOrder));
        } catch (OptimisticLockingFailureException e) {
            throw new CustomValidationException("valid.book.stock.out.date");
        }
    }

    private void getAndUpdateBook(BookOrder bookOrder) {
        final BookStock bookStock = Optional.of(bookOrder)
                .map(BookOrder::getBook)
                .map(BookStock::getId)
                .flatMap(bookRepository::findById)
                .filter(Objects::nonNull)
                .filter(book -> bookOrder.getQuantity() <= book.getQuantity())
                .orElseThrow(() -> new CustomValidationException("valid.book.stock.not.exist",
                        bookOrder.getBook().getId()));

        bookStock.setQuantity(bookStock.getQuantity() - bookOrder.getQuantity());
        bookOrder.setPrice(bookStock.getPrice());
        bookOrder.setBook(bookStock);
    }

    @Override
    @Logging
    public CustomerOrderDto getOrderById(Long customerOrderId) {
        return orderRepository.findById(customerOrderId)
                .map(customerOrderMapper::toDTO)
                .orElse(null);
    }

    @Override
    @Logging
    public Page<CustomerOrderDto> getOrdersBetweenDates(OrderSearchRequest searchRequest) {
        if (searchRequest.getStartDate().isAfter(searchRequest.getEndDate())) {
            throw new CustomValidationException("valid.date.error", searchRequest);
        }
        return orderRepository.findCustomerOrderByCreateDateTimeBetween(searchRequest.getStartDate(),
                        searchRequest.getEndDate(), searchRequest.getPageRequestInfo().getPageRequest())
                .map(customerOrderMapper::toDTO);
    }

    @Override
    @Logging
    public List<OrderStatistics> getStatistics() {
        return orderRepository.findOrderStatistics();
    }
}
