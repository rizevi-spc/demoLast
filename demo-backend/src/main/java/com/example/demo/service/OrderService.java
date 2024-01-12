package com.example.demo.service;

import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.OrderSearchRequest;
import com.example.demo.dto.OrderStatistics;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * service for order operations
 */
public interface OrderService {
    /**
     * inser order
     */
    CustomerOrderDto add(CustomerOrderDto customerDto);

    /**
     * get order by id
     */
    CustomerOrderDto getOrderById(Long customerOrderId);

    /**
     * get order between dates with pagination
     */
    Page<CustomerOrderDto> getOrdersBetweenDates(OrderSearchRequest searchRequest);

    /**
     * get order statistics
     */
    List<OrderStatistics> getStatistics();
}
