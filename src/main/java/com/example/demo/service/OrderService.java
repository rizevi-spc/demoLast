package com.example.demo.service;

import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.OrderSearchRequest;
import com.example.demo.dto.OrderStatistics;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    CustomerOrderDto add(CustomerOrderDto customerDto);

    CustomerOrderDto getOrderById(Long customerOrderId);

    Page<CustomerOrderDto> getOrdersBetweenDates(OrderSearchRequest searchRequest);

    List<OrderStatistics> getStatistics();
}
